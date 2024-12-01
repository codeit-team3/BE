package com.codeit.sprint.team3.backend.bookclub.adapter.exception;

public class BookClubNotExistException extends BookClubException {
    private static final String MESSAGE = "존재하지 않는 북클럽입니다.";

    public BookClubNotExistException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
