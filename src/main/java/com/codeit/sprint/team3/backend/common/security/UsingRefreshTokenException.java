package com.codeit.sprint.team3.backend.common.security;

public class UsingRefreshTokenException extends RuntimeException {
    public UsingRefreshTokenException(String message) {
        super(message);
    }
}
