package ee.taltech.dotaStats.service;

import ee.taltech.dotaStats.service.Dota.Match;
import ee.taltech.dotaStats.service.Dota.Player;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DotaCalculation {


    public static DotaResponse.MostUsedHero calculate_mostUsedHero(List<Match> matches, String playerId, Map<Integer, Long> heroIDOccurrences) {
        int winCount = 0;

        Integer mostUsedHeroID = heroIDOccurrences.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

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

        double winPercentage = (double) winCount / (double) heroIDOccurrences.get(mostUsedHeroID);
        long matchesPlayed = heroIDOccurrences.get(mostUsedHeroID);

        return new DotaResponse.MostUsedHero(mostUsedHeroID, matchesPlayed, winCount, winPercentage);
    }

    public static DotaResponse.LeastUsedHero calculate_leastUsedHero(List<Match> matches, String playerId, Map<Integer, Long> heroIDOccurrences){
        Integer leastUsedHeroID = heroIDOccurrences.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
        return new DotaResponse.LeastUsedHero(leastUsedHeroID);
    }




}
