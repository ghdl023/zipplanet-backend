package com.kh.zipplanet.domain.user.service;

import com.kh.zipplanet.domain.user.mapper.UserMapper;
import com.kh.zipplanet.domain.user.model.*;
import com.kh.zipplanet.domain.user.model.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserMapper mapper;

    public User getUserByUserId(String userId) throws Exception {
        return mapper.getUserByUserId(userId);
    }

    public User getUserByUsername(String username) throws Exception {
        return mapper.getUserByUsername(username);
    }

    public User login(UserLoginRequest userLoginRequest) throws Exception {
        return mapper.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
    }

    public int signUp(UserSignupRequest userSignupRequest) throws Exception {
        return mapper.signUp(userSignupRequest);
    }

    public User findId(UserFindIdRequest userFindIdRequest) throws Exception {
        return mapper.findId(userFindIdRequest.getPhone());
    }

    public String findPwd(UserFindPwdRequest userFindPwdRequest) throws Exception {
        return mapper.findPwd(userFindPwdRequest.getUsername(), userFindPwdRequest.getPhone());
    }

    public int updateUser(UserUpdateRequest userUpdateRequest) throws Exception {
        return mapper.updateUser(userUpdateRequest);
    }

    public User findUniqueUsername(String username) throws Exception {
        return mapper.findUnique(username, "", "");
    }
    public User findUniqueNickname(String nickname) throws Exception {
        return mapper.findUnique("" , nickname, "");
    }
    public User findUniquePhone(String phone) throws Exception {
        return mapper.findUnique("", "", phone);
    }

    public int deleteUser(String username) throws Exception {
        return mapper.deleteUser(username);
    }
}
