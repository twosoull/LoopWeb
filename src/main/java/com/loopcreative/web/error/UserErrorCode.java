package com.loopcreative.web.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{

    EXCEPTION_BASIC(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"잠시 서비스를 이용하실 수 없습니다."),
    INACTIVE_USER(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN, "User is inactive"),
    NO_RESULT(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, "결과가 없습니다."),
    FAIL_UPLOAD(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"파일 저장에 실패했습니다."),
    FAIL_POST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"잘못 된 접근입니다."),
    VALID_USER_ID(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"아이디를 입력해주세요."),
    VALID_USER_PW(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"패스워드를 입력해주세요."),
    FAIL_USER(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"아이디와 패스워드를 확인해주세요."),
    NOT_USER(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"로그인이 필요한 메뉴입니다."),
    FAIL_FILE_REMOVE(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"파일 삭제에 실패했습니다."),
    FAIL_FILE_DOWNLOAD(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"파일 다운로드에 실패했습니다."),
    FAIL_MAIL_SEND(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,"메일 전송에 실패했습니다.");


    private final int status;
    private final HttpStatus httpStatus;
    private final String message;
}
