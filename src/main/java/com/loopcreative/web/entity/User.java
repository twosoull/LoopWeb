package com.loopcreative.web.entity;

import com.loopcreative.web.dto.UserDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_no")
    private Long id;

    private String userId;
    private String userPw;

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public UserDto chageDto(){
        UserDto dto = new UserDto();

        dto.setId(this.id);
        dto.setUserId(this.userId);
        return dto;
    }
}
