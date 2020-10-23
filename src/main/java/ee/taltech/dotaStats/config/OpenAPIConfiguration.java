package ee.taltech.dotaStats.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Dota 2 Player Stats",
                description = "" +
                        "This is a simple Dota 2 player stats API that returns competitive rankings and calculates most used hero as well as least used hero based on all matches."),
        servers = @Server(url = "http://localhost:8080")
)

class OpenAPIConfiguration {
}