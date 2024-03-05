package com.kh.zipplanet.global.common;

import lombok.Data;

@Data
public class CommonResponse {
    private StatusEnum status;
    private String message;
    private Object data;

    public CommonResponse() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
