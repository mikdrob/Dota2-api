package Project1.Team5.service.Dota;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode
@Data
public class Player {

    @JsonProperty("match_id")
    private long match_id;
    @JsonProperty("assists")
    private int assists;
    @JsonProperty("kills")
    private int kills;
}
