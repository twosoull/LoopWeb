package com.loopcreative.web.admin.validate;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import org.springframework.data.domain.Page;

public class ContactAdminServiceVali {


    public void hasList(Page<Contact>  findContacts){
        if(findContacts == null){
            throw new RestApiException(UserErrorCode.NO_RESULT);
        }
    }
}
