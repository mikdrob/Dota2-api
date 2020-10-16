package Project1.Team5.service.Dota;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
@Data
public class Player {
    public String getSoloRank() { return solo_competative_rank; }

    public String getCompetativeRank() { return competative_rank; }

    public List getMMREstimate() { return mmr_estimate; }

    @JsonProperty("solo_competative_rank")
    private String solo_competative_rank;

    @JsonProperty("competitive_rank")
    private String competative_rank;

    @JsonProperty("mmr_estimate")
    private List mmr_estimate;
}
