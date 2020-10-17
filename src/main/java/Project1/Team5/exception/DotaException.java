package Project1.Team5.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "unknown symbol")
public class DotaException extends RuntimeException {
    public DotaException(String message){super(message);}
}
