package Project1.Team5.service;

import Project1.Team5.service.Dota.Match;
import Project1.Team5.service.Dota.Player;
//import Project1.Team5.service.Dota.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Data
@Service
public class DataQuery  {

    RestTemplate restTemplate = new RestTemplate();


    public DotaResponse dataQuery(String playerId){

        ResponseEntity < List <Match>> rateResponse = restTemplate.exchange("https://api.opendota.com/api/players/"+playerId+"/matches?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", HttpMethod.GET, null, new ParameterizedTypeReference < List <Match>> () {});;
        List<Match> matches = rateResponse.getBody();


        ResponseEntity<Player> entity;
        entity = restTemplate.getForEntity("https://api.opendota.com/api/players/"+playerId+"?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", Player.class);
        Player rankResponse = entity.getBody();

        ArrayList<Integer> heroIDList = new ArrayList<Integer>();

        int winCount = 0;

        for (Match match : matches) {
            heroIDList.add(match.getHero_id());
        }


        Map<Integer, Long> heroIDOccurrences =
                heroIDList.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        Integer mostUsedHeroID = heroIDOccurrences.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        Integer leastUsedHeroID = heroIDOccurrences.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();

        for (Match match : matches) {
            if (match.getHero_id() == mostUsedHeroID) {
                //Which slot the player is in. 0-127 are Radiant, 128-255 are Dire
                if (match.isRadiant_win() && match.getPlayer_slot() < 128) {
                    winCount += 1;
                } else if (!match.isRadiant_win() && match.getPlayer_slot() > 127) {
                    winCount += 1;
                }
            }
        }

        double winPercentage = ((double) heroIDOccurrences.get(mostUsedHeroID) - (double) winCount) / (double) heroIDOccurrences.get(mostUsedHeroID);
        long matchesPlayed = heroIDOccurrences.get(mostUsedHeroID);

        DotaResponse.MostUsedHero mostUsedHero = new DotaResponse.MostUsedHero(mostUsedHeroID,matchesPlayed,winCount,winPercentage);
        DotaResponse.LeastUsedHero leastUsedHero = new DotaResponse.LeastUsedHero(leastUsedHeroID);
        DotaResponse dotaResponse = new DotaResponse(
                rankResponse.getProfile().getName(),
                rankResponse.getSoloRank(),
                rankResponse.getCompetitiveRank(),
                rankResponse.getMmr_estimate().getEstimate(),
                mostUsedHero,leastUsedHero
        );

        return dotaResponse;


    }
}
