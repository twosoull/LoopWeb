package com.loopcreative.web.front.validate;

import com.loopcreative.web.entity.Work;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkServiceVali {

    public void hasList(List<Work> works){

        if(works.size() <= 0){
            throw new RestApiException(UserErrorCode.NO_RESULT);
        }
    }
}
