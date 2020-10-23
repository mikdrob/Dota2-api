package ee.taltech.dotaStats.service;

import ee.taltech.dotaStats.service.Dota.Match;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DotaCalculation {

    public static Map<Integer, Long> calculateHeroIDOCcurrences(List<Match> matches) {
        ArrayList<Integer> heroIDList = new ArrayList<Integer>();

        for (Match match : matches) {
            heroIDList.add(match.getHero_id());
        }

        return heroIDList.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));


    }

    public static DotaResponse.MostUsedHero calculate_mostUsedHero(List<Match> matches, Map<Integer, Long> heroIDOccurrences) {
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

    public static DotaResponse.LeastUsedHero calculate_leastUsedHero(List<Match> matches, Map<Integer, Long> heroIDOccurrences){
        Integer leastUsedHeroID = heroIDOccurrences.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
        return new DotaResponse.LeastUsedHero(leastUsedHeroID);
    }




}
