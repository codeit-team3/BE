package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request;

import com.codeit.sprint.team3.backend.bookclub.adapter.exception.IllegalTypeConversionException;
import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;

public enum BookClubListOrderType {
    DESC, //최신 순
    END, //마감임박 순
    ;

    public static OrderType from(String orderType) {
        return switch (orderType.toUpperCase()) {
            case "DESC" -> OrderType.DESC;
            case "END" -> OrderType.END;
            default -> throw new IllegalTypeConversionException(orderType);
        };
    }

    //TODO: 읔... 리팩토링하자...
    public static OrderType myBookClubOrderType(String orderType) {
        return switch (orderType.toUpperCase()) {
            case "DESC" -> OrderType.DESC;
            case "ASC" -> OrderType.ASC;
            default -> throw new IllegalTypeConversionException(orderType);
        };
    }
}
