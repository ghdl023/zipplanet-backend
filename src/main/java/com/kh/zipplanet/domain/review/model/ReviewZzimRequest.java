package com.kh.zipplanet.domain.review.model;

import lombok.Data;

@Data
public class ReviewZzimRequest {
    private int userId;
    private int reviewId;
    private String zzimYn;
}
