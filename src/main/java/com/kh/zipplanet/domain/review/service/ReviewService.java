package com.kh.zipplanet.domain.review.service;

import com.kh.zipplanet.domain.review.mapper.ReviewMapper;
import com.kh.zipplanet.domain.review.model.ReviewCreateRequest;
import com.kh.zipplanet.domain.review.model.ReviewDeleteRequest;
import com.kh.zipplanet.domain.review.model.ReviewUpdateRequest;
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

    public List<ReviewVo> search(String searchType, String keyword, String gu, String dong, String contractTypeId, int rate, String pos, String sort, int offset, int limit) {
        if(searchType.equals("pos")) {
            return reviewMapper.searchByPos(pos, sort, offset, limit);
        } else if(searchType.equals(("keyword"))){
            return reviewMapper.searchByKeyword(keyword, sort, offset, limit);
        } else {
            return reviewMapper.searchByFilter(gu, dong, contractTypeId, rate, sort, offset, limit);
        }
    }

    public int searchTotalCount(String searchType, String keyword, String gu, String dong, String contractTypeId, int rate, String pos, String sort, int offset, int limit) {
        if(searchType.equals("pos")) {
            return reviewMapper.searchByPosTotalCount(pos, sort, offset, limit);
        } else if(searchType.equals(("keyword"))){
            return reviewMapper.searchByKeywordTotalCount(keyword, sort, offset, limit);
        } else {
            return reviewMapper.searchByFilterTotalCount(gu, dong, contractTypeId, rate, sort, offset, limit);
        }
    }

    public int update(ReviewUpdateRequest reviewUpdateRequest) {
        return reviewMapper.update(reviewUpdateRequest);
    }

    public int delete(ReviewDeleteRequest reviewDeleteRequest) {
        return reviewMapper.delete(reviewDeleteRequest);
    }

    public List<ReviewVo> searchMyReview(int userId){
        return reviewMapper.searchMyReview(userId);
    }
}
