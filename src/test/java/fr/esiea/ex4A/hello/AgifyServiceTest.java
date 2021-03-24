package fr.esiea.ex4A.hello;
import fr.esiea.ex4A.lovemeet.AgifyService;
import fr.esiea.ex4A.lovemeet.UserAgify;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AgifyServiceTest {
    @ParameterizedTest
    @CsvSource({
        "Sébastien,FR",
        "Samba,IL",
        "Samy,FR"
    })
    void agifyService_testIT(String name, String country){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.agify.io/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        AgifyService agifyService = retrofit.create(AgifyService.class);

        try {
            Response<UserAgify> execute = agifyService.getAgeOf(name, country).execute();
            UserAgify ageOf = execute.body();
            if(ageOf == null){
                fail();
                return;
            }
            assertEquals(ageOf.name, name);
            assertEquals(ageOf.country_id, country);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
