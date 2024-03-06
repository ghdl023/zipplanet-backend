package com.kh.zipplanet.domain.user.service;

import com.kh.zipplanet.domain.user.mapper.UserMapper;
import com.kh.zipplanet.domain.user.model.LoginRequest;
import com.kh.zipplanet.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserMapper mapper;

    public List<User> getUser() {
        return mapper.getUser();
    }

    public User login(LoginRequest loginRequest) {
        return mapper.login(loginRequest.getId(), loginRequest.getPw());
    }
}
