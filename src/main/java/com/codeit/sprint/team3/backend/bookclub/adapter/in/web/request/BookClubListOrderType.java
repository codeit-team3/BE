package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request;

import com.codeit.sprint.team3.backend.bookclub.domain.OrderType;

public enum BookClubListOrderType {
    DESC, //최신 순
    END, //마감임박 순
    SOON, //가까운 모임 날짜 순
    ;

    public static OrderType from(String orderType) {
        return switch (orderType.toUpperCase()) {
            case "DESC" -> OrderType.DESC;
            case "END" -> OrderType.END;
            case "SOON" -> OrderType.SOON;
            default -> throw new IllegalArgumentException("지원하지 않는 정렬방식 : " + orderType);
        };
    }
}
