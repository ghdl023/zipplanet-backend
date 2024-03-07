package com.kh.zipplanet.domain.review.mapper;

import com.kh.zipplanet.domain.review.model.ReviewCreateRequest;
import com.kh.zipplanet.domain.review.model.ReviewVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewMapper {

    int create(ReviewCreateRequest reviewCreateRequest);

    List<ReviewVo> search(@Param("pos") String pos);
}
