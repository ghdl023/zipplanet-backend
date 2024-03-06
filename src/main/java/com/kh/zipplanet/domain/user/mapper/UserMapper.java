package com.kh.zipplanet.domain.user.mapper;

import com.kh.zipplanet.domain.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<User> getUser();

    User login(@Param("id") String id,@Param("pw") String pw);
}
