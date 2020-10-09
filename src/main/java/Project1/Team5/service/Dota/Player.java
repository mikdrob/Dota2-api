package Project1.Team5.service.Dota;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Player {

    @JsonProperty("match_id")
    private int match_id;
    @JsonProperty("assists")
    private int assists;
    @JsonProperty("kills")
    private int kills;
    @JsonProperty("total_gold")
    private int total_gold;
    @JsonProperty("total_xp")
    private int total_xp;

}
