package com.kh.zipplanet.domain.user.model;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
