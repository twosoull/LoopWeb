package com.loopcreative.web.front.validate;

import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.form.ContactForm;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class ContactServiceVali {

    /**
     * @deprecated 삭제예정 form에서 @Valid처리 진행했음
     * @param c
     */
    public void saveValidation(ContactForm c){
        if(StringUtils.isEmpty(c.getContactClientCompany())){
            throw new RestApiException(UserErrorCode.FAIL_POST);
        }
    }
}
