package ee.taltech.dotaStats.controller;



import ee.taltech.dotaStats.service.DataQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class StatsControllerMockMvcTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DataQuery dataQuery;

    @Test
    void statsController_returns_stats() throws Exception {
        mvc.perform(get("/stats?playerId=100616105").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player_name").isNotEmpty())
                .andExpect(jsonPath("$.solo_competitive_rank").exists())
                .andExpect(jsonPath("$.competitive_rank").exists())
                .andExpect(jsonPath("$.mmr_estimate").exists())
                .andExpect(jsonPath("$.most_used_hero.hero_id").exists())
                .andExpect(jsonPath("$.most_used_hero.matches_played").exists())
                .andExpect(jsonPath("$.most_used_hero.matches_won").exists())
                .andExpect(jsonPath("$.most_used_hero.win_percentage").exists())
                .andExpect(jsonPath("$.least_used_hero.hero_id").exists());
    }



}