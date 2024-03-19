package com.loopcreative.web.admin.validate;

import com.loopcreative.web.entity.Work;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.form.WorkForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class WorkAdminServiceVali {

    /**
     * 1. list size가 0일 경우 UserErrorCode.NO_RESULT 예외 반환
     * @param works
     */
    public void hasList(Page<Work> works){
        if(works.getSize() <= 0){
            throw new RestApiException(UserErrorCode.NO_RESULT);
        }
    }

}
