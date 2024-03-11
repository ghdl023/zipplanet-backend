package com.kh.zipplanet.domain.review.model;

import lombok.Data;

@Data
public class ReviewDeleteRequest {
    private int reviewId;
    private int userId;
}
