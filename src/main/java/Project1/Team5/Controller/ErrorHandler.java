package Project1.Team5.Controller;

import Project1.Team5.exception.DotaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.OffsetDateTime;
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(DotaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleStatDbBadRequest(DotaException ex) {
        return map(ex, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse map(DotaException ex, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setHttpStatus(status.value());
        return response;
    }


}
