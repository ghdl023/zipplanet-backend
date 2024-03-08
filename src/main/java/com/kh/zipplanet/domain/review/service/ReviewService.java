package com.kh.zipplanet.domain.review.service;

import com.kh.zipplanet.domain.review.mapper.ReviewMapper;
import com.kh.zipplanet.domain.review.model.ReviewCreateRequest;
import com.kh.zipplanet.domain.review.model.ReviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    public int create(ReviewCreateRequest reviewCreateRequest) {
        return reviewMapper.create(reviewCreateRequest);
    }

    public List<ReviewVo> search(String pos) {
        return reviewMapper.search(pos);
    }

    public List<ReviewVo> searchByFilter(String keyword, String gu, String dong, String contractTypeId, int rate, String sort) {
        return reviewMapper.searchByFilter(keyword, gu, dong, contractTypeId, rate, sort);
    }
}
