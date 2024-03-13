package com.loopcreative.web.admin.validate;

import com.loopcreative.web.entity.Work;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class WorkAdminServiceVali {

    public void hasList(Page<Work> works){
        if(works.getSize() <= 0){
            throw new RestApiException(UserErrorCode.NO_RESULT);
        }
    }
}
