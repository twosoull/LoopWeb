package com.loopcreative.web.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, "파라미터 확인이 필요합니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.NOT_FOUND.value(),HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final int status;
    private final HttpStatus httpStatus;
    private final String message;

}