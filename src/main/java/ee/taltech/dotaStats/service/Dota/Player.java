package ee.taltech.dotaStats.service.Dota;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data

public class Player {
    public String getSoloRank() {
        return solo_competitive_rank;
    }

    public String getCompetitiveRank() {
        return competitive_rank;
    }

    public MMR getMmr_estimate() {
        return mmr_estimate;
    }

    public Profile getProfile() {
        return profile;
    }

    @JsonProperty("solo_competitive_rank")
    private String solo_competitive_rank;

    @JsonProperty("competitive_rank")
    private String competitive_rank;

    @JsonProperty("mmr_estimate")
    private MMR mmr_estimate;

    @JsonProperty("profile")
    private Profile profile;

    @Data
    public class MMR {
        public int getEstimate() {
            return estimate;
        }

        @JsonProperty("estimate")
        private int estimate;

    }

    @Data
    public class Profile {
        public String getName() { return name; }

        @JsonProperty("personaname")
        private String name;

    }

}

