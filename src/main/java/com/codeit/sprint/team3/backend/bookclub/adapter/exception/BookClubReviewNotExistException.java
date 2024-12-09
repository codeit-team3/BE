package com.codeit.sprint.team3.backend.bookclub.adapter.exception;

public class BookClubReviewNotExistException extends BookClubException {
    private static final String MESSAGE = "존재하지 않는 북클럽 리뷰입니다.";

    public BookClubReviewNotExistException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
