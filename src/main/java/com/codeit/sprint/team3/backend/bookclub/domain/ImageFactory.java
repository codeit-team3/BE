package com.codeit.sprint.team3.backend.bookclub.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageFactory {
    private static final String IMAGE_URL = "https://codeit-bookco.s3.ap-northeast-2.amazonaws.com/%s/%d/%s";

    public String createImageUrl(String domain, Long id, String imageName, Boolean hasImage) {
        if (hasImage == null || !hasImage) {
            return "";
        }
        return String.format(IMAGE_URL, domain, id, imageName);
    }
}