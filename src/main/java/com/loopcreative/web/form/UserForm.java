package com.loopcreative.web.form;

import com.loopcreative.web.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForm {

    private String userNo;

    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String userPw;

    public User toEntity(){
        User user = new User();
        user.setUserId(this.getUserId());
        user.setUserPw(this.getUserPw());
        return user;
    }
}
