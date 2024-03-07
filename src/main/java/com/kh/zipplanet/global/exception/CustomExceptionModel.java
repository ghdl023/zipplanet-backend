package com.kh.zipplanet.global.exception;


public record CustomExceptionModel(
        String message,
        String errorCode,
        String hint
) {
}