package com.kh.zipplanet.domain.user.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String id;
    private String pw;
}
