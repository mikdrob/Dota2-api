package ee.taltech.dotaStats.controller;


import ee.taltech.dotaStats.service.DataQuery;
import ee.taltech.dotaStats.service.DotaResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping({"/stats", "/stat2"})
@Tag(name = "Player Stats", description = "Return player hero stats & rank based on matches")
@RestController
public class StatsController {

    @GetMapping
    @ApiOperation(value = "Get stats for given playerId", response = Iterable.class, tags = "getStats")
    public DotaResponse index(@RequestParam String playerId, @RequestParam(defaultValue = "false") Boolean displayMatches) {
        log.info("Player ID - " + playerId);
        DataQuery dataquery = new DataQuery();
        log.info("response from https://api.opendota.com/api/players/" + dataquery.dataQuery(playerId, displayMatches));
        return dataquery.dataQuery(playerId, displayMatches);
    }


}
