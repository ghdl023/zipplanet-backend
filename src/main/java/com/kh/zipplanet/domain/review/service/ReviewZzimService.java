package com.kh.zipplanet.domain.review.service;

import com.kh.zipplanet.domain.review.mapper.ReviewZzimMapper;
import com.kh.zipplanet.domain.review.model.ReviewVo;
import com.kh.zipplanet.domain.review.model.ReviewZzimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewZzimService {

    @Autowired
    ReviewZzimMapper reviewZzimMapper;

    public int updateReviewZzim(ReviewZzimRequest reviewZzimRequest) {
        int result = 0;
        int findId = 0;

        try {
            findId = reviewZzimMapper.findReviewZzim(reviewZzimRequest);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        if(findId == 0) {
            result = reviewZzimMapper.insertReviewZzim(reviewZzimRequest);
        } else {
            result = reviewZzimMapper.updateReviewZzim(reviewZzimRequest);
        }

        return result;
    }

    public List<ReviewVo> searchMyZzim(int userId){
        return reviewZzimMapper.searchMyZzim(userId);
    }

}
