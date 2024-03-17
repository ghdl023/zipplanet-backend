package com.kh.zipplanet.domain.review.service;

import com.kh.zipplanet.domain.review.mapper.ReviewReportMapper;
import com.kh.zipplanet.domain.review.model.ReviewReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewReportService {

    @Autowired
    ReviewReportMapper reviewReportMapper;

    public int reportReview(ReviewReportRequest reviewReportRequest) {
        return reviewReportMapper.reportReview(reviewReportRequest);
    }

    public int findByReviewIdAndUserId(ReviewReportRequest reviewReportRequest) {
        return reviewReportMapper.findByReviewIdAndUserId(reviewReportRequest.getReviewId(), reviewReportRequest.getUserId());
    }
}
