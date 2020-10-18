package ee.taltech.dotaStats.service.Dota;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class Match {
    public int getHero_id() {
        return hero_id;
    }

    public int getPlayer_slot() {
        return player_slot;
    }

    public boolean isRadiant_win() {
        return radiant_win;
    }

    @JsonProperty("hero_id")
    private int hero_id;

    @JsonProperty("player_slot")
    private int player_slot;

    @JsonProperty("radiant_win")
    private boolean radiant_win;
}
