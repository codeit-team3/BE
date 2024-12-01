package com.codeit.sprint.team3.backend.bookclub.adapter.exception;

import lombok.Getter;

@Getter
public class InvalidRequest extends BookClubException {
    private static final String MESSAGE = "잘못된 요청입니다.";

    public String fieldName;
    public String message;

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    public int getStatusCode() {
        return 400;
    }
}
