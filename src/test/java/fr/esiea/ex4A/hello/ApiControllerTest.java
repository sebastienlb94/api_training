package fr.esiea.ex4A.hello;
import fr.esiea.ex4A.lovemeet.AgifyService;
import fr.esiea.ex4A.lovemeet.Repository;
import fr.esiea.ex4A.lovemeet.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ApiControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private AgifyService agifyService;

    @MockBean
    private Repository repository;

    ApiControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @Order(1)
    void get_matches_no_users_registered() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/matches")
                .queryParam("userName", "Seb")
                .queryParam("userCountry", "FR")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("""
                  []
                """)
            );
    }

    @Test
    @Order(2)
    void get_matches_few_users_registered() throws Exception {
        List<User> users = List.of(
            new User("test@et.esiea.fr", "Max", "Max01", "FR", "M", "F", 23),
            new User("test@et.esiea.fr", "Samba", "Samba02", "IT", "M", "M", 23),
            new User("test@et.esiea.fr", "Marc", "Marc03", "FR", "M", "F", 51),
            new User("test@et.esiea.fr", "Marie", "Marie04", "FR", "F", "M", 29)
        );

        doReturn(users).when(repository).getUsers();

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/matches")
                .queryParam("userName", "Max")
                .queryParam("userCountry", "FR")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("""
                  [
                      {
                        "name": "Max",
                        "twitter": "Max01"
                      },
                      {
                        "name": "Marc",
                        "twitter": "Marc03"
                      }
                    ]
                """)
            );

        verify(repository, times(2)).getUsers();
    }

    @Test
    @Order(3)
    void add_user() throws Exception {
        doReturn(52).when(agifyService).getAge(anyString(), anyString());

        mockMvc
            .perform(MockMvcRequestBuilders.post(
                "/api/inscription").contentType(MediaType.APPLICATION_JSON).content("""
                  {
                    "userEmail": "machin@truc.com",
                    "userName": "machin",
                    "userTweeter": "machin45",
                    "userCountry": "FR",
                    "userSex": "M",
                    "userSexPref": "M"
                  }      
                """)
            )
            .andExpect(status().isCreated())
            .andExpect(content().json("""
                  {
                   "userEmail": "machin@truc.com",
                   "userName": "machin",
                   "userTweeter": "machin45",
                   "userCountry": "FR",
                   "userSex": "M",
                   "userSexPref": "M",
                   "userAge": 52
                  }
                """)
            );

        verify(agifyService, times(1)).getAge(anyString(), anyString());
    }

    @Test
    @Order(4)
    void get_users_list() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

}
