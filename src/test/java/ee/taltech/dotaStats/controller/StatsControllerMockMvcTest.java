package ee.taltech.dotaStats.controller;



import ee.taltech.dotaStats.service.DataQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    @Test
    void statsController_returns_error() throws Exception {
        mvc.perform(get("/stats?playerId=999999999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Player not found or no available data."));
    }
    

}