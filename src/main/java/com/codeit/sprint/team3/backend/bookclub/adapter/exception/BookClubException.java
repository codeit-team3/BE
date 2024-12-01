package com.codeit.sprint.team3.backend.bookclub.adapter.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BookClubException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public BookClubException(String message) {
        super(message);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}

