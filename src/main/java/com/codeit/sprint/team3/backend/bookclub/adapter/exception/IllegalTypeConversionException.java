package com.codeit.sprint.team3.backend.bookclub.adapter.exception;

public class IllegalTypeConversionException extends BookClubException {
    private static final String MESSAGE = "변환할 수 없는 타입입니다. : %s";

    public IllegalTypeConversionException(String type) {
        super(String.format(MESSAGE, type));
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
