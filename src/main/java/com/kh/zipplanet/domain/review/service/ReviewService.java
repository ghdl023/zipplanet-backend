package com.kh.zipplanet.domain.review.service;

import com.kh.zipplanet.domain.review.mapper.ReviewMapper;
import com.kh.zipplanet.domain.review.model.ReviewCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    public int create(ReviewCreateRequest reviewCreateRequest) {
        return reviewMapper.create(reviewCreateRequest);
    }
}
