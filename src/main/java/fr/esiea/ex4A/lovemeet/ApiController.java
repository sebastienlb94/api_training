package fr.esiea.ex4A.lovemeet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    private final AgifyService agifyService;
    private final Repository repository;

    public ApiController(AgifyService agifyService, Repository repository) {
        this.agifyService = agifyService;
        this.repository = repository;
    }

    @PostMapping("/api/inscription")
    public ResponseEntity<?> addUser(@RequestBody Map<String, String> request) throws IOException {
        String userName = request.get("userName");
        String userCountry = request.get("userCountry");
        Integer userAge = agifyService.getAge(userName, userCountry);

        User user = new User(request.get("userEmail"), userName, request.get("userTweeter"), userCountry, request.get("userSex"), request.get("userSexPref"), userAge);
        repository.getUsers().add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<?> getUserList() {
        return new ResponseEntity<>(repository.getUsers(), HttpStatus.OK);
    }


    @GetMapping("/api/matches")
    public ResponseEntity<?> getMatches(@RequestParam("userName") String userName, @RequestParam("userCountry") String userCountry) {
        Optional<User> opRequestUser = null;

        opRequestUser = repository.getUsers().stream().filter(user -> user.getUserName().equalsIgnoreCase(userName) && user.getUserCountry().equalsIgnoreCase(userCountry)).findFirst();

        // user not found
        if (opRequestUser.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<User>(), HttpStatus.OK);
        }

        // find matches for user
        List<Match> matches = new ArrayList<>();
        User requestUser = opRequestUser.get();
        matches = repository.getUsers().stream()
            .filter(user -> user != requestUser)
            .filter(user -> requestUser.getUserSexPref().equalsIgnoreCase(user.getUserSex()))
            .filter(user -> requestUser.getUserSex().equalsIgnoreCase(user.getUserSexPref()))
            .filter(user -> Math.abs(user.getUserAge() - requestUser.getUserAge()) <= 4)
            .map(user -> new Match(user.getUserName(), user.getUserTweeter()))
            .collect(Collectors.toList());

        return new ResponseEntity<>(matches, HttpStatus.OK);
    }
}
