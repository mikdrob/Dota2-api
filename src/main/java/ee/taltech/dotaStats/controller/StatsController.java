package ee.taltech.dotaStats.controller;


import ee.taltech.dotaStats.service.DataQuery;
import ee.taltech.dotaStats.service.DotaResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/stats")
@RestController
public class StatsController {

    @GetMapping
    public DotaResponse index(@RequestParam String playerId) {
        DataQuery dataquery = new DataQuery();
        return dataquery.dataQuery(playerId);
    }


}
