package com.loopcreative.web.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    //EX(Front에서 보여지는 오류 숫자(500,400 등), 실제 백엔드에서 오류상황(코드를 보내줘야 하므로 일반적으로는 OK를 쳐야한다),오류 메세지 혹은 공통코드)
    EXCEPTION_BASIC(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"잠시 서비스를 이용하실 수 없습니다."),
    INACTIVE_USER(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK, "User is inactive"),
    VALID_USER_ID(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"아이디를 입력해주세요."),
    VALID_USER_PW(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"패스워드를 입력해주세요."),
    NO_RESULT(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK, "결과가 없습니다."),
    NOT_USER(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"로그인이 필요한 메뉴입니다."),
    FAIL_UPLOAD(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"파일 저장에 실패했습니다."),
    FAIL_USER(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"아이디와 패스워드를 확인해주세요."),
    FAIL_POST(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"잘못 된 접근입니다."),
    FAIL_FILE_REMOVE(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"파일 삭제에 실패했습니다."),
    FAIL_FILE_DOWNLOAD(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"파일 다운로드에 실패했습니다."),
    FAIL_MAIL_SEND(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK,"메일 전송에 실패했습니다."),
    FAIL_REMOVE_FILE_NO_RESULT(HttpStatus.BAD_REQUEST.value(),HttpStatus.OK, "삭제할 파일 정보를 찾을 수 없습니다."),;


    private final int status;
    private final HttpStatus httpStatus;
    private final String message;
}
