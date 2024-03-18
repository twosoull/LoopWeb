package com.loopcreative.web.form;

import com.loopcreative.web.entity.User;
import lombok.Data;

@Data
public class UserForm {

    private String userNo;
    private String userId;
    private String userPw;

    public User toEntity(){
        User user = new User();
        user.setUserId(this.getUserId());
        user.setUserPw(this.getUserPw());
        return user;
    }
}
