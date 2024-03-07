package com.kh.zipplanet.domain.review.mapper;

import com.kh.zipplanet.domain.review.model.ReviewCreateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReviewMapper {

    int create(ReviewCreateRequest reviewCreateRequest);
}
