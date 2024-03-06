package com.kh.zipplanet.member.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MemberLoginRequestDto {
    private String memberId;
    private String password;
}