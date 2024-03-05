package com.kh.zipplanet.domain.user.mapper;

import com.kh.zipplanet.domain.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@MapperScan
public interface UserMapper {
    User getUser();
}
