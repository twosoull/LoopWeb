package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.service.UserAdminService;
import com.loopcreative.web.dto.UserDto;
import com.loopcreative.web.form.UserForm;
import com.loopcreative.web.util.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginAdminController {

    private final UserAdminService userAdminService;

    @PostMapping("/admin/login")
    public ResponseEntity<Message> login(UserForm userForm, HttpServletRequest request){

        UserDto loginUser = userAdminService.Login(userForm);
        HttpSession session = request.getSession();
        session.setAttribute("admin",loginUser);
        session.setMaxInactiveInterval(3600);

        return new ResponseEntity<>(new Message(loginUser.getUserId()), HttpStatus.OK);
    }

    @PostMapping("/admin/login/join")
    public ResponseEntity<Message> join(UserForm userForm){

        String joinUserId = userAdminService.join(userForm);

        return new ResponseEntity<>(new Message(joinUserId), HttpStatus.OK);
    }

    @PostMapping("/admin/login/logout")
    public ResponseEntity<Message> logout(HttpServletRequest request){

        HttpSession session = request.getSession();

        session.invalidate();
        return new ResponseEntity<>(new Message(), HttpStatus.OK);
    }

}
