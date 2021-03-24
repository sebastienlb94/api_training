package fr.esiea.ex4A.hello;

import fr.esiea.ex4A.Launcher;
import fr.esiea.ex4A.lovemeet.AgifyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LauncherTest {
    @Test

    void agifyService_test(){
        Launcher mock = new Launcher();
        AgifyService agifyService = mock.agifyService();
        assertNotNull(agifyService);
    }
}
