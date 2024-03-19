package com.loopcreative.web.admin.validate;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ContactAdminServiceVali {

    /**
     * 1. 리스트가 없을 경우 UserErrorCode.NO_RESULT 예외 반환
     * @param findContacts
     */
    public void hasList(Page<Contact>  findContacts){
        if(findContacts.getSize() <= 0){
            throw new RestApiException(UserErrorCode.NO_RESULT);
        }
    }
}
