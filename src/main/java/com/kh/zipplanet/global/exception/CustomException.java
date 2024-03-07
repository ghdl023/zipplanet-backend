package com.kh.zipplanet.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomException extends RuntimeException {
    private String message;
    private String errorCode;
    private String hint;
}