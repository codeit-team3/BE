package com.codeit.sprint.team3.backend.bookclub.domain;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.IllegalTypeConversionException;

public enum MeetingType {
    ONLINE,
    OFFLINE,
    ALL
    ;

    public static MeetingType getCommandType(String type) {
        return switch (type.toUpperCase()) {
            case "ONLINE" -> ONLINE;
            case "OFFLINE" -> OFFLINE;
            default -> throw new IllegalTypeConversionException(type);
        };
    }

    public static MeetingType getQueryType(String type) {
        return switch (type.toUpperCase()) {
            case "ONLINE" -> ONLINE;
            case "OFFLINE" -> OFFLINE;
            case "ALL" -> ALL;
            default -> throw new IllegalTypeConversionException(type);
        };
    }
}
