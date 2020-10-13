package Project1.Team5.service.Dota;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class Response {

    private List<Player> players ;
}
