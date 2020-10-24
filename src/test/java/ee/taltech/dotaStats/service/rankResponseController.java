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

import java.util.Collections;
import java.util.List;
import java.util.Map;


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
    void ControllerHeroIDOCcurrences() {
        ResponseEntity<Match> rateResponse = testRestTemplate.exchange("/stats?playerId=100616105&displayMatches=true", HttpMethod.GET, null, new ParameterizedTypeReference<Match>() {
        });
        Match matches = rateResponse.getBody();

        Map<Integer, Long> heroIDOccurrences = calculateHeroIDOCcurrences(Collections.singletonList(matches));;

        assertNotNull(heroIDOccurrences);

    }


}
