package Project1.Team5.service;

import lombok.Data;



@Data
public class DotaResponse {

    //TODO: Add constructor

    private String player_name;
    private int solo_competitive_rank;
    private int competitive_rank;
    private MMR_estimate mmr_estimate = new MMR_estimate();
    private MostUsedHero most_used_hero = new MostUsedHero();
    private LeastUsedHero least_used_hero = new LeastUsedHero();

    @Data
    class MostUsedHero {
        private int hero_id;
        private int matches_played;
        private int matches_won;
        private int win_percentage;
    }

    @Data
    class LeastUsedHero {
        private int hero_id;
    }

    @Data
    class MMR_estimate {
        private int estimate;
        private int stdDev;
        private int n;
    }


}

