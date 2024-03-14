package com.kh.zipplanet.domain.user.model;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String password;
    private String nickname;
    private String phone;
    private String address;
    private String username;
}
