package com.kh.zipplanet.domain.review.model;

import lombok.Data;

import java.util.List;

@Data
public class ReviewListPagingResponse {
    private List<ReviewVo> reviews;
    private int totalCount;
    private boolean hasNext;
}
