package com.codeit.sprint.team3.backend.bookclub.domain;

public enum MeetingType {
    ONLINE,
    OFFLINE,
    ALL
    ;

    public static MeetingType getCommandType(String type) {
        return switch (type.toUpperCase()) {
            case "ONLINE" -> ONLINE;
            case "OFFLINE" -> OFFLINE;
            default -> throw new IllegalArgumentException("변환할 수 없는 타입입니다.: " + type);
        };
    }

    public static MeetingType getQueryType(String type) {
        return switch (type.toUpperCase()) {
            case "ONLINE" -> ONLINE;
            case "OFFLINE" -> OFFLINE;
            case "ALL" -> ALL;
            default -> throw new IllegalArgumentException("변환할 수 없는 타입입니다.: " + type);
        };
    }
}
