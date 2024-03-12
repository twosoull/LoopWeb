package com.loopcreative.web.front.validate;

import com.loopcreative.web.form.ContactForm;
import org.thymeleaf.util.StringUtils;

public class ContactSerivceVali {

    public void saveValidation(ContactForm c){
        if(StringUtils.isEmpty(c.getContactClientCompany())){

        }
    }
}
