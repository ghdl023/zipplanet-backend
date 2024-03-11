package com.kh.zipplanet.domain.user.mapper;

import com.kh.zipplanet.domain.user.model.User;
import com.kh.zipplanet.domain.user.model.UserSignupRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<User> getUser();
    User getUserByUserId(@Param("userId") String userId);
    User getUserByUsername(@Param("username") String username);

    User login(@Param("username") String username, @Param("password") String password);

//    User signUp(@Param("username") String username, @Param("password") String password, @Param("nicname") String nickname, @Param("address") String address, @Param("phone") String phone);
    int signUp(UserSignupRequest userSignupRequest);

    User findId(@Param("phone") String phone);

    User findPwd(@Param("username") String username, @Param("phone") String phone);
}
