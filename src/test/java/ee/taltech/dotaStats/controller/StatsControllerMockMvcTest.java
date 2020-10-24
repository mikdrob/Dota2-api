package ee.taltech.dotaStats.controller;



import ee.taltech.dotaStats.service.DataQuery;
import ee.taltech.dotaStats.service.Dota.Match;
import ee.taltech.dotaStats.service.Dota.Player;
import ee.taltech.dotaStats.service.DotaResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static ee.taltech.dotaStats.service.DotaCalculation.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc

public class StatsControllerMockMvcTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestTemplate restTemplate;

    @MockBean
    private DataQuery dataQuery;


    @Test
    void statsController_returns_stats_with_matches() throws Exception {
        ResponseEntity<List<Match>> rateResponse = restTemplate.exchange("https://api.opendota.com/api/players/100616105/matches?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", HttpMethod.GET, null, new ParameterizedTypeReference<List<Match>>() {});
        List<Match> matches = rateResponse.getBody();

        ResponseEntity<Player> entity = restTemplate.getForEntity("https://api.opendota.com/api/players/100616105?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", Player.class);
        Player rankResponse = entity.getBody();

        Map<Integer, Long> heroIDOccurrences = calculateHeroIDOCcurrences(matches);

        Mockito.when(dataQuery.dataQuery("100616105", true)).thenReturn(
                new DotaResponse(
                        rankResponse.getProfile().getName(),
                        rankResponse.getSoloRank(),
                        rankResponse.getCompetitiveRank(),
                        rankResponse.getMmr_estimate().getEstimate(),
                        calculate_mostUsedHero(matches, heroIDOccurrences),
                        calculate_leastUsedHero(matches, heroIDOccurrences),
                        matches
                )
        );
        mvc.perform(get("/stats?playerId=100616105&displayMatches=yes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player_name").isNotEmpty())
                .andExpect(jsonPath("$.solo_competitive_rank").exists())
                .andExpect(jsonPath("$.competitive_rank").exists())
                .andExpect(jsonPath("$.mmr_estimate").exists())
                .andExpect(jsonPath("$.most_used_hero.hero_id").exists())
                .andExpect(jsonPath("$.most_used_hero.matches_played").exists())
                .andExpect(jsonPath("$.most_used_hero.matches_won").exists())
                .andExpect(jsonPath("$.most_used_hero.win_percentage").exists())
                .andExpect(jsonPath("$.least_used_hero.hero_id").exists())
                .andExpect(jsonPath("$.matches").exists());
    }


    @Test
    void statsController_returns_stats_without_matches() throws Exception {
        mvc.perform(get("/stats?playerId=100616105&displayMatches=no").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player_name").isNotEmpty())
                .andExpect(jsonPath("$.solo_competitive_rank").exists())
                .andExpect(jsonPath("$.competitive_rank").exists())
                .andExpect(jsonPath("$.mmr_estimate").exists())
                .andExpect(jsonPath("$.most_used_hero.hero_id").exists())
                .andExpect(jsonPath("$.most_used_hero.matches_played").exists())
                .andExpect(jsonPath("$.most_used_hero.matches_won").exists())
                .andExpect(jsonPath("$.most_used_hero.win_percentage").exists())
                .andExpect(jsonPath("$.least_used_hero.hero_id").exists())
                .andExpect(jsonPath("$.matches").doesNotExist());
    }

    @Test
    void statsController_returns_error() throws Exception {
        mvc.perform(get("/stats?playerId=999999999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Player not found or no available data."));
    }

    @Test
    void apiDocs_returns_stats_path() throws Exception {
        mvc.perform(get("/api-docs").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths./stats").exists())
                .andExpect(jsonPath("$.info.title").value("Dota 2 Player Stats"));
    }

}