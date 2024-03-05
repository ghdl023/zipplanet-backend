package com.kh.zipplanet.domain.user.service;

import com.kh.zipplanet.domain.user.mapper.UserMapper;
import com.kh.zipplanet.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserMapper mapper;

    public User getUser() {
        return mapper.getUser();
    }
}
