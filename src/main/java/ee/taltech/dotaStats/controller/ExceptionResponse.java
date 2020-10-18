package ee.taltech.dotaStats.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ExceptionResponse {

    private String message;
    private int httpStatus;
    private OffsetDateTime timestamp;
}
