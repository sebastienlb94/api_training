package fr.esiea.ex4A.lovemeet;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class MeetController {
    @PostMapping(path = "api/inscription")
    void registerUser(@RequestBody User user){
        System.out.println(user);
    }

    @GetMapping(path = "api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Match> getMatches(String name, String country){

        return new MatchResponse(new Match("Sebastien", "Sebastienlb94"),
            new Match("Test", "test123"),
            new Match("Chips", "ChipsSalé"));
    }
}
