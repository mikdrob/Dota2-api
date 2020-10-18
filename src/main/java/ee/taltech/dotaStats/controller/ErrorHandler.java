package ee.taltech.dotaStats.controller;

import ee.taltech.dotaStats.exception.StatsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(StatsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleStatDbBadRequest(StatsException ex) {
        return map(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(java.util.NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleNullExternalAPIResponse() {
        StatsException ex = new StatsException("Player not found or no available data.");
        return map(ex, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse map(StatsException ex, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setHttpStatus(status.value());
        response.setTimestamp(OffsetDateTime.now());
        return response;
    }
}