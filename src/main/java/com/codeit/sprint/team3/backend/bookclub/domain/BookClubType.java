package com.codeit.sprint.team3.backend.bookclub.domain;

public enum BookClubType {
    FREE,
    FIXED
    ;

    public static BookClubType from(String type) {
        return switch (type.toUpperCase()) {
            case "FREE" -> FREE;
            case "FIXED" -> FIXED;
            default -> throw new IllegalArgumentException("변환할 수 없는 타입입니다.: " + type);
        };
    }
}
