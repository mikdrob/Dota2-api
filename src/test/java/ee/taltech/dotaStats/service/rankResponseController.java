package ee.taltech.dotaStats.service;

import ee.taltech.dotaStats.service.Dota.Match;
import ee.taltech.dotaStats.service.Dota.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ee.taltech.dotaStats.service.DotaCalculation.calculate_leastUsedHero;
import static ee.taltech.dotaStats.service.DotaCalculation.calculate_mostUsedHero;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class rankResponseController {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void controllerBodyIsNotNull() {
        ResponseEntity<Player> entity = testRestTemplate.getForEntity("/stats?playerId=100616105", Player.class);
        Player rankResponse = entity.getBody();
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(rankResponse);
        assertNotNull(rankResponse.getSoloRank());
        assertNotNull(rankResponse.getCompetitiveRank());
         assertNotNull(rankResponse.getMmr_estimate().getEstimate());
    }

    @Test
    void controllerBodyErrorHandling() {
        ResponseEntity<Player> entity = testRestTemplate.getForEntity("/stats?playerId=324", Player.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

    @Test
    void controllerCalculations() {
        ResponseEntity<List<Match>> rateResponse = testRestTemplate.exchange("https://api.opendota.com/api/players/100616105/matches?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", HttpMethod.GET, null, new ParameterizedTypeReference<List<Match>>() {
        });
        List<Match> matches = rateResponse.getBody();

        ArrayList<Integer> heroIDList = new ArrayList<Integer>();

        for (Match match : matches) {
            heroIDList.add(match.getHero_id());
        }

        Map<Integer, Long> heroIDOccurrences =
                heroIDList.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));


        DotaResponse.MostUsedHero mostUsedHero = calculate_mostUsedHero(matches, heroIDOccurrences);
        DotaResponse.LeastUsedHero leastUsedHero = calculate_leastUsedHero(matches, heroIDOccurrences);

        assertNotNull(mostUsedHero);
        assertNotNull(leastUsedHero);

    }
}
