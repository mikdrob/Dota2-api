package ee.taltech.dotaStats.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import ee.taltech.dotaStats.service.Dota.Match;
import lombok.Data;

import java.util.List;


@Data
public class DotaResponse {

    private String player_name;
    private String solo_competitive_rank;
    private String competitive_rank;
    private int mmr_estimate;
    private MostUsedHero most_used_hero;
    private LeastUsedHero least_used_hero;
    //private Matches[] matches;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Match> matches;

    public DotaResponse(String player_name, String solo_competitive_rank, String competitive_rank, int mmr_estimate, MostUsedHero most_used_hero, LeastUsedHero least_used_hero, List<Match> matches ) {
        this.player_name = player_name;
        this.solo_competitive_rank = solo_competitive_rank;
        this.competitive_rank = competitive_rank;
        this.mmr_estimate = mmr_estimate;
        this.most_used_hero = most_used_hero;
        this.least_used_hero = least_used_hero;
        this.matches = matches;

    }

    @Data
    static
    class MostUsedHero {
        private int hero_id;
        private long matches_played;
        private long matches_won;
        private double win_percentage;

        public MostUsedHero(int hero_id, long matches_played, long matches_won, double win_percentage) {
            this.hero_id = hero_id;
            this.matches_played = matches_played;
            this.matches_won = matches_won;
            this.win_percentage = win_percentage;
        }
    }

    @Data
    static
    class LeastUsedHero {
        public LeastUsedHero(int hero_id) { this.hero_id = hero_id; }

        private int hero_id;
    }


    @Data
    static
    class Matches {

        public Matches(List<Match> matches) { this.matches = matches; }

        private List<Match> matches;

    }
    

}

