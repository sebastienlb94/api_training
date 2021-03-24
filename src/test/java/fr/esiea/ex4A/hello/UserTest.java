package fr.esiea.ex4A.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esiea.ex4A.lovemeet.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

    @ParameterizedTest
    @ValueSource(strings = {"{\"userEmail\":\"ssoto@et.esiea.fr\",\"userName\":\"Sébastien\",\"userTweeter\":\"Sebastienlb94\",\"userCountry\":\"FR\",\"userSex\":\"M\",\"userSexPref\":\"F\"}"
    })
    void user_test(String json){
        try {
            User user = new ObjectMapper().readValue(json, User.class);
            System.out.println(user.toString());
            Assertions.assertTrue(json.contains(user.userCountry));
            Assertions.assertTrue(json.contains(user.userEmail));
            Assertions.assertTrue(json.contains(user.userName));
            Assertions.assertTrue(json.contains(user.userTweeter));
            Assertions.assertTrue(json.contains(user.userSex.name()));
            Assertions.assertTrue(json.contains(user.userSexPref.name()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

}
