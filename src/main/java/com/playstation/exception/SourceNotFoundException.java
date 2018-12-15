package com.playstation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class SourceNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Source is irresponsive.";

    public SourceNotFoundException() {
        super(MESSAGE);
    }
}
