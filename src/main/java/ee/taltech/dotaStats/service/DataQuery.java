package ee.taltech.dotaStats.service;

import ee.taltech.dotaStats.service.Dota.Match;
import ee.taltech.dotaStats.service.Dota.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ee.taltech.dotaStats.service.DotaCalculation.calculate_leastUsedHero;
import static ee.taltech.dotaStats.service.DotaCalculation.calculate_mostUsedHero;

@EqualsAndHashCode
@Data
@Service
public class DataQuery {

    RestTemplate restTemplate = new RestTemplate();

    public DotaResponse dataQuery(String playerId) {
        ResponseEntity<List<Match>> rateResponse = restTemplate.exchange("https://api.opendota.com/api/players/" + playerId + "/matches?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", HttpMethod.GET, null, new ParameterizedTypeReference<List<Match>>() {
        });
        List<Match> matches = rateResponse.getBody();


        ResponseEntity<Player> entity;
        entity = restTemplate.getForEntity("https://api.opendota.com/api/players/" + playerId + "?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", Player.class);
        Player rankResponse = entity.getBody();

        ArrayList<Integer> heroIDList = new ArrayList<Integer>();

        for (Match match : matches) {
            heroIDList.add(match.getHero_id());
        }

        Map<Integer, Long> heroIDOccurrences =
                heroIDList.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));


        DotaResponse.MostUsedHero mostUsedHero = calculate_mostUsedHero(matches, heroIDOccurrences);
        DotaResponse.LeastUsedHero leastUsedHero = calculate_leastUsedHero(matches, heroIDOccurrences);

        DotaResponse dotaResponse = new DotaResponse(
                rankResponse.getProfile().getName(),
                rankResponse.getSoloRank(),
                rankResponse.getCompetitiveRank(),
                rankResponse.getMmr_estimate().getEstimate(),
                mostUsedHero, leastUsedHero
        );

        return dotaResponse;

    }
}