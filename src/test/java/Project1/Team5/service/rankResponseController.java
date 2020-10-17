package Project1.Team5.service;

import Project1.Team5.service.Dota.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


class rankResponseController {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void controllerBodyIsNotNull() {
        ResponseEntity<Player> entity = testRestTemplate.getForEntity("/stats?playerId=100616105", Player.class);
        Player rankResponse = entity.getBody();
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(rankResponse);

        assertNotNull(rankResponse.getProfile().getName());
        assertNotNull(rankResponse.getSoloRank());
        assertNotNull(rankResponse.getCompetitiveRank());
        assertNotNull(rankResponse.getMmr_estimate().getEstimate());
    }

}