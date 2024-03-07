package com.kh.zipplanet.domain.user.model;

import lombok.Data;

@Data
public class UserSignupRequest {
    private String username;
    private String password;
    private String passwordConfirm;
    private String nickname;
    private String phone;
    private String address = "";
}
