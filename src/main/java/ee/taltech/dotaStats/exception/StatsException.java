package ee.taltech.dotaStats.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "unknown symbol")
public class StatsException extends RuntimeException {
    public StatsException(String message) {
        super(message);
    }
}
