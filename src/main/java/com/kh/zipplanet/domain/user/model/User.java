package com.kh.zipplanet.domain.user.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private int userId;
    private String roleName;
    private String username;
    private String nickname;
    private String phone;
    private String address;
    private LocalDate createDate;
    private LocalDate deleteDate;
    private char deleteYn;
}
