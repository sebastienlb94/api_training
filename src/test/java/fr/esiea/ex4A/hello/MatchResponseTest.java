package fr.esiea.ex4A.hello;
import fr.esiea.ex4A.lovemeet.Match;
import fr.esiea.ex4A.lovemeet.MatchResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class MatchResponseTest {
    @ParameterizedTest
    @ValueSource(strings = {"""
        Sébastien,Sebastienlb94
        Pierre,Pierrre
        Test,Testeur
        """
    })
    void matchesresponse_filling_list_test(String NomNTwitter){
        List<Match> identifiants = Arrays.stream(NomNTwitter.split("\n")).map(s -> {
            String[] kv = s.split(",");
            return new Match(kv[0],kv[1]);
        }).collect(Collectors.toList());

        MatchResponse matches = new MatchResponse(identifiants);

        matches.forEach(m -> assertTrue(identifiants.contains(m)));

    }
    @ParameterizedTest
    @CsvSource({
        "Sebastien,Sebastien2,Sebastien3,Sebastien4,Sebastien5,Sebastien6"
    })
    void test_matchresponse(String Nom1, String Twitter1, String Nom2, String Twitter2, String Nom3, String Twitter3){
        List<Match> identifiants = Arrays.asList(new Match(Nom1, Twitter1),
            new Match(Nom2, Twitter2),
            new Match(Nom3, Twitter3));
        MatchResponse matches = new MatchResponse(identifiants.get(0),
            identifiants.get(1),
            identifiants.get(2));

        matches.forEach(m -> assertTrue(identifiants.contains(m)));
    }
}

