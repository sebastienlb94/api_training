package fr.esiea.ex4A.lovemeet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchResponse extends ArrayList<Match> {

    public MatchResponse(List<Match> matchs){
        super();
        this.addAll(matchs);
    }

    public MatchResponse(Match... matchs){
        super();
        this.addAll(Arrays.asList(matchs));
    }
}
