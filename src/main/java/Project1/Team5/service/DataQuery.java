package Project1.Team5.service;

import Project1.Team5.service.Dota.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataQuery extends Response  {


    RestTemplate restTemplate = new RestTemplate();




    //public DataQuery() {
     //   super(metadata);
    //}

    public DotaResponse dataQuery(){

       ResponseEntity<Response> entity;
       entity = restTemplate.getForEntity("https://api.opendota.com/api/matches/271145476?api_key=1d67e82f-c0f0-4e49-bf0d-7a4e2bc537e2", Response.class);
       Response response  =entity.getBody();
       DotaResponse dotaResponse = new DotaResponse();

      //dotaResponse.setAssists(response.getPlayer().);

       return dotaResponse;




    }
}
