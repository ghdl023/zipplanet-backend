package com.kh.zipplanet.domain.review.service;

import com.kh.zipplanet.domain.review.mapper.ReviewMapper;
import com.kh.zipplanet.domain.review.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    public int createReview(ReviewCreateRequest reviewCreateRequest) {
        return reviewMapper.createReview(reviewCreateRequest);
    }

    public List<ReviewVo> search(String searchType, String keyword, String gu, String dong, String contractTypeId, int rate, String pos, String sort, int offset, int limit, String userId) {
        if(searchType.equals("pos")) {
            return reviewMapper.searchByPos(getLat(pos), getLng(pos), sort, offset, limit, userId);
        } else if(searchType.equals(("keyword"))){
            return reviewMapper.searchByKeyword(keyword, sort, offset, limit, userId);
        } else {
            return reviewMapper.searchByFilter(gu, dong, contractTypeId, rate, sort, offset, limit, userId);
        }
    }

    public int searchTotalCount(String searchType, String keyword, String gu, String dong, String contractTypeId, int rate, String pos, String sort, int offset, int limit) {
        if(searchType.equals("pos")) {
            return reviewMapper.searchByPosTotalCount(getLat(pos), getLng(pos), sort, offset, limit);
        } else if(searchType.equals(("keyword"))){
            return reviewMapper.searchByKeywordTotalCount(keyword, sort, offset, limit);
        } else {
            return reviewMapper.searchByFilterTotalCount(gu, dong, contractTypeId, rate, sort, offset, limit);
        }
    }

    public int updateReview(ReviewUpdateRequest reviewUpdateRequest) {
        return reviewMapper.updateReview(reviewUpdateRequest);
    }

    public int deleteReview(ReviewDeleteRequest reviewDeleteRequest) {
        return reviewMapper.deleteReview(reviewDeleteRequest);
    }

    public List<PosVo> selectAllPos() {
        return reviewMapper.selectAllPos();
    }

    public ReviewVo findById(String reviewId, String userId) {
        ReviewVo result = reviewMapper.findById(reviewId, userId);
        upHit(reviewId);
        return result;
    }

    public ReviewVo checkDuplicateByUserIdAndPos(int userId, String pos) {
        ReviewVo result = reviewMapper.findByUserIdAndPos(userId, pos);
        return result;
    }

    private int upHit(String reviewId) {
        return reviewMapper.upHit(reviewId);
    }

    public List<ReviewVo> searchMyReview(int userId){
        return reviewMapper.searchMyReview(userId);
    }



    private String getLat(String pos) {
        if(pos == null || pos.equals("")) return "";
        return pos.split(",")[0];
    }

    private String getLng(String pos) {
        if(pos == null || pos.equals("")) return "";
        return pos.split(",")[1];
    }

}
