package com.loopcreative.web.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{

    EXCEPTION_BASIC(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"잠시 서비스를 이용하실 수 없습니다."),
    INACTIVE_USER(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN, "User is inactive"),
    BASIC_EXCEPTION(HttpStatus.BAD_REQUEST.value(),HttpStatus.FORBIDDEN,"fasfa");

    private final int status;
    private final HttpStatus httpStatus;
    private final String message;
}
