package com.kh.zipplanet.domain.review.mapper;

import com.kh.zipplanet.domain.review.model.ReviewZzimRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReviewZzimMapper {
    int findReviewZzim(ReviewZzimRequest reviewZzimRequest);

    int insertReviewZzim(ReviewZzimRequest reviewZzimRequest);

    int updateReviewZzim(ReviewZzimRequest reviewZzimRequest);
}
