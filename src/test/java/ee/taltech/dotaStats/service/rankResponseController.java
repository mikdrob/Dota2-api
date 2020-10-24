package ee.taltech.dotaStats.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.dotaStats.service.Dota.Match;
import ee.taltech.dotaStats.service.Dota.Player;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static ee.taltech.dotaStats.service.DotaCalculation.*;
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
    void ServiceHeroIDOCcurrences() {
        ResponseEntity<Match> rateResponse = testRestTemplate.exchange("/stats?playerId=100616105&displayMatches=true", HttpMethod.GET, null, new ParameterizedTypeReference<Match>() {
        });
        Match matches = rateResponse.getBody();

        Map<Integer, Long> heroIDOccurrences = calculateHeroIDOCcurrences(Collections.singletonList(matches));;

        assertNotNull(heroIDOccurrences);

    }

    @Test
    void ServiceMostUsedHero() {
        ResponseEntity<Match> rateResponse = testRestTemplate.exchange("/stats?playerId=100616105&displayMatches=true", HttpMethod.GET, null, new ParameterizedTypeReference<Match>() {
        });
        Match matches = rateResponse.getBody();

        ArrayList<Integer> heroIDList = new ArrayList<Integer>();

        for (Match match : Collections.singletonList(matches)) {
            heroIDList.add(match.getHero_id());
        }

        Map<Integer, Long> heroIDOccurrences = heroIDList.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        DotaResponse.MostUsedHero mostUsedHero = calculate_mostUsedHero(Collections.singletonList(matches), heroIDOccurrences);

        assertNotNull(mostUsedHero);

    }

    @Test
    void ServiceLeastUsedHero() {
        ResponseEntity<Match> rateResponse = testRestTemplate.exchange("/stats?playerId=100616105&displayMatches=true", HttpMethod.GET, null, new ParameterizedTypeReference<Match>() {
        });
        Match matches = rateResponse.getBody();

        ArrayList<Integer> heroIDList = new ArrayList<Integer>();

        for (Match match : Collections.singletonList(matches)) {
            heroIDList.add(match.getHero_id());
        }

        Map<Integer, Long> heroIDOccurrences = heroIDList.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        DotaResponse.LeastUsedHero leastUsedHero = calculate_leastUsedHero(Collections.singletonList(matches), heroIDOccurrences);

        assertNotNull(leastUsedHero);

    }


}
