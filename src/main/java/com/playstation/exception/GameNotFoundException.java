package com.playstation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Game not found in list.";

    public GameNotFoundException() {
        super(MESSAGE);
    }
}
