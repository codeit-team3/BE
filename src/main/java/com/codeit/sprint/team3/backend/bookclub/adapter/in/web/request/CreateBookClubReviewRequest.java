package com.codeit.sprint.team3.backend.bookclub.adapter.in.web.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookClubReviewRequest(
        @Max(value = 5, message = "별점은 최대 5점 입니다.")
        @Min(value = 1, message = "별점은 최소 1점 입니다.")
        @NotNull(message = "별점은 필수입니다.")
        Integer rating,
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        String content
) {
}
