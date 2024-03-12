package com.kh.zipplanet.domain.review.model;

import lombok.Data;

@Data
public class ReviewReportRequest {
    private int reviewId;
    private int userId;
    private int reportTypeId;
}
