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

    List<ReviewVo> searchByPos(@Param("pos") String pos, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
    int searchByPosTotalCount(@Param("pos") String pos, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
    List<ReviewVo> searchByKeyword(@Param("keyword") String keyword, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
    int searchByKeywordTotalCount(@Param("keyword") String keyword, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
    List<ReviewVo> searchByFilter(@Param("gu") String gu, @Param("dong") String dong, @Param("contractTypeId") String contractTypeId, @Param("rate") int rate, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
    int searchByFilterTotalCount(@Param("gu") String gu, @Param("dong") String dong, @Param("contractTypeId") String contractTypeId, @Param("rate") int rate, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);

    int updateReview(@Param("reviewUpdateRequest") ReviewUpdateRequest reviewUpdateRequest);

    int deleteReview(@Param("reviewDeleteRequest") ReviewDeleteRequest reviewDeleteRequest);

    List<PosVo> selectAllPos();
}
