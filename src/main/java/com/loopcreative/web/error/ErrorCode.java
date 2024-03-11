package com.loopcreative.web.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String name();
    int getStatus();
    HttpStatus getHttpStatus();
    String getMessage();

}
