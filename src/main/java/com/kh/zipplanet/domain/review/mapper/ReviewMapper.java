package com.kh.zipplanet.domain.review.mapper;

import com.kh.zipplanet.domain.review.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewMapper {

    int createReview(@Param("reviewCreateRequest") ReviewCreateRequest reviewCreateRequest);

    List<ReviewVo> searchByPos(@Param("lat") String lat, @Param("lng") String lng, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("userId") String userId);
    int searchByPosTotalCount(@Param("lat") String lat, @Param("lng") String lng, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
    List<ReviewVo> searchByKeyword(@Param("keyword") String keyword, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("userId") String userId);
    int searchByKeywordTotalCount(@Param("keyword") String keyword, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
    List<ReviewVo> searchByFilter(@Param("gu") String gu, @Param("dong") String dong, @Param("contractTypeId") String contractTypeId, @Param("rate") int rate, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("userId") String userId);
    int searchByFilterTotalCount(@Param("gu") String gu, @Param("dong") String dong, @Param("contractTypeId") String contractTypeId, @Param("rate") int rate, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);

    int updateReview(@Param("reviewUpdateRequest") ReviewUpdateRequest reviewUpdateRequest);

    int deleteReview(@Param("reviewDeleteRequest") ReviewDeleteRequest reviewDeleteRequest);

    List<PosVo> selectAllPos();

    ReviewVo findById(@Param("reviewId") String reviewId, @Param("userId") String userId);

    ReviewVo findByUserIdAndPos(@Param("userId") int userId, @Param("pos") String pos);

    int upHit(@Param("reviewId") String reviewId);

    List<ReviewVo> searchMyReview(@Param("userId") int userId);

    List<ReviewVo> searchMyZzim(@Param("userId") int userId);
}
