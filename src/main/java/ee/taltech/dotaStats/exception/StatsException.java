package ee.taltech.dotaStats.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "unknown symbol")
public class StatsException extends RuntimeException {
    public StatsException(String message) {
        super(message);
        log.error("Caught exception " + message);
    }
}
