package fr.esiea.ex4A.hello;
import fr.esiea.ex4A.lovemeet.AgifyService;
import fr.esiea.ex4A.lovemeet.AgifyServiceMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

class AgifyServiceTest {

    @Mock
    private final AgifyService agifyService;

    AgifyServiceTest() {
        this.agifyService = new AgifyService(AgifyServiceMock.getAgifyClientMock());
    }

    @Test
    void get_age() throws IOException {
        assertThat(agifyService.getAge("Seb", "FR")).isEqualTo(55);
    }
}
