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

    List<ReviewVo> searchByFilter(@Param("keyword") String keyword, @Param("gu") String gu, @Param("dong") String dong, @Param("contractTypeId") String contractTypeId, @Param("rate") int rate, @Param("sort") String sort);
}
