package com.kh.zipplanet.domain.review.mapper;

import com.kh.zipplanet.domain.review.model.ReviewReportRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReviewReportMapper {

    int reportReview(@Param("reviewReportRequest") ReviewReportRequest reviewReportRequest);

    int findByReviewIdAndUserId(@Param("reviewId") int reviewId, @Param("userId") int userId);
}
