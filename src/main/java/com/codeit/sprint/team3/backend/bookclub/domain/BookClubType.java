package com.codeit.sprint.team3.backend.bookclub.domain;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.IllegalTypeConversionException;

public enum BookClubType {
    FREE,
    FIXED,
    ALL
    ;

    public static BookClubType getCommandType(String type) {
        return switch (type.toUpperCase()) {
            case "FREE" -> FREE;
            case "FIXED" -> FIXED;
            default -> throw new IllegalTypeConversionException(type);
        };
    }

    public static BookClubType getQueryType(String type) {
        return switch (type.toUpperCase()) {
            case "FREE" -> FREE;
            case "FIXED" -> FIXED;
            case "ALL" -> ALL;
            default -> throw new IllegalTypeConversionException(type);
        };
    }
}
