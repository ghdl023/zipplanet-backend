package com.kh.zipplanet.domain.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class User {
    private int userId;
    private int roleId;
    private String accountId;
    private String accountPwd;
    private String nickname;
    private String address;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    private char deleteYn;
    private String phone;
}
