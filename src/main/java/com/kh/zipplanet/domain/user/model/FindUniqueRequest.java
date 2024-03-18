package com.kh.zipplanet.domain.user.model;
import lombok.Data;
@Data
public class FindUniqueRequest {
    private String username;
    private String nickname;
    private String phone;
}
