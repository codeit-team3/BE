package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.IllegalTypeConversionException;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;

public enum BookClubReviewListOrderType {
    DESC,
    RATE_DESC,
    RATE_ASC,
    ;

    public static OrderType from(String orderType) {
        return switch (orderType.toUpperCase()) {
            case "DESC" -> OrderType.DESC;
            case "RATE_DESC" -> OrderType.RATE_DESC;
            case "RATE_ASC" -> OrderType.RATE_ASC;
            default -> throw new IllegalTypeConversionException(orderType);
        };
    }
}
