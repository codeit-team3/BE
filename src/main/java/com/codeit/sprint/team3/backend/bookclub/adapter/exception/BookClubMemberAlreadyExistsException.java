package com.codeit.sprint.team3.backend.bookclub.adapter.exception;

public class BookClubMemberAlreadyExistsException extends BookClubException {
    private static final String MESSAGE = "이미 참가중인 북클럽입니다.";

    public BookClubMemberAlreadyExistsException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
