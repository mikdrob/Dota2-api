package Project1.Team5.service;

import Project1.Team5.service.Dota.Player;
import Project1.Team5.service.Dota.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EqualsAndHashCode
@Data
@Service
public class DataQuery extends Response  {


    RestTemplate restTemplate = new RestTemplate();

    public DotaResponse dataQuery(){


       DotaResponse dotaResponse = new DotaResponse();
       ResponseEntity<List<Player>> rateResponse = restTemplate.exchange("https://api.opendota.com/api/players/100616105/recentMatches?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Player>>() {
                        });
        List<Player> players = rateResponse.getBody();
        for (Player player: players
             ) {
            dotaResponse.setKills(player.getKills());

        }

        return dotaResponse;





    }
}
