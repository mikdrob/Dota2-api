package ee.taltech.dotaStats.controller;


import ee.taltech.dotaStats.service.DataQuery;
import ee.taltech.dotaStats.service.DotaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/stats")
@RestController
public class StatsController {

    @GetMapping
    public DotaResponse index(@RequestParam String playerId) {
        log.info("Player ID - " + playerId);
        DataQuery dataquery = new DataQuery();
        log.info("response from https://api.opendota.com/api/players/" + dataquery.dataQuery(playerId));
        return dataquery.dataQuery(playerId);
    }


}
