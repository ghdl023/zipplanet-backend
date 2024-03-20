package com.kh.zipplanet.domain.review.mapper;

import com.kh.zipplanet.domain.review.model.ReviewVo;
import com.kh.zipplanet.domain.review.model.ReviewZzimRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewZzimMapper {
    int findReviewZzim(ReviewZzimRequest reviewZzimRequest);

    int insertReviewZzim(ReviewZzimRequest reviewZzimRequest);

    int updateReviewZzim(ReviewZzimRequest reviewZzimRequest);

    List<ReviewVo> searchMyZzim(@Param("userId") int userId);

}
