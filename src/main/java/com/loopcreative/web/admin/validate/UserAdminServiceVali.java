package com.loopcreative.web.admin.validate;

import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.form.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserAdminServiceVali {

    /**
     * 1. form 필수 값이 null일 경우 예외 반환
     * @param userForm
     */
    public void userValid(UserForm userForm){
        if(ObjectUtils.isEmpty(userForm.getUserId())){
            throw new RestApiException(UserErrorCode.VALID_USER_ID);
        }
        if(ObjectUtils.isEmpty(userForm.getUserPw())){
            throw new RestApiException(UserErrorCode.VALID_USER_PW);
        }
    }
}
