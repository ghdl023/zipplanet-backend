package com.kh.zipplanet.domain.user.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private int userId;
    private String roleName;
    private String username;
    private String nickname;
    private String phone;
    private String address;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    private char deleteYn;
}
