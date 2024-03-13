package com.kh.zipplanet.domain.user.model;

import lombok.Data;

@Data
public class UserFindPwdRequest {
    private String username;
    private String phone;
}
