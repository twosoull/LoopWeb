package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.service.UserAdminService;
import com.loopcreative.web.dto.UserDto;
import com.loopcreative.web.form.UserForm;
import com.loopcreative.web.util.Message;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginAdminController {

    private final UserAdminService userAdminService;

    /**
     * 1. session에 로그인 회원 담기 (Password 담지 않음)
     * 2. 로그인 유저의 id 반환
     * @param userForm
     * @param request
     * @return
     */
    @PostMapping("/admin/login")
    public ResponseEntity<Message> login(@RequestBody @Valid UserForm userForm, HttpServletRequest request
    , HttpServletResponse response) throws Exception{

        UserDto loginUser = userAdminService.Login(userForm);
        HttpSession session = request.getSession();
        session.setAttribute("admin",loginUser);
        session.setMaxInactiveInterval(3600);

        Cookie cookie = new Cookie("userId", loginUser.getUserId());
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntity<>(new Message(loginUser.getUserId()), HttpStatus.OK);
    }

    /**
     * 1. session에 로그인 회원 담기 (Password 담지 않음)
     * 2. 로그인 유저의 id 반환
     * @param userForm
     * @param request
     * @return
     */
    @PostMapping("/admin/login1")
    public ResponseEntity<Message> login1(@Valid UserForm userForm, HttpServletRequest request) throws Exception{

        UserDto loginUser = userAdminService.Login(userForm);
        HttpSession session = request.getSession();
        session.setAttribute("admin",loginUser);
        session.setMaxInactiveInterval(3600);

        return new ResponseEntity<>(new Message(loginUser.getUserId()), HttpStatus.OK);
    }

    /**
     * 1. 회원가입 메소드
     * 2. loopCreative는 관리계정이 하나이므로 사용하지 않음
     * 3. Message.data = 가입회원 Id 리턴
     * @param userForm
     * @return
     */
    @PostMapping("/admin/login/join")
    public ResponseEntity<Message> join(@Valid UserForm userForm){

        String joinUserId = userAdminService.join(userForm);

        return new ResponseEntity<>(new Message(joinUserId), HttpStatus.OK);
    }

    /**
     * 1. 로그아웃
     * 2. 접근 시 세션만료
     * @param request
     * @return
     */
    @PostMapping("/admin/login/logout")
    public ResponseEntity<Message> logout(HttpServletRequest request){

        HttpSession session = request.getSession();

        session.invalidate();
        return new ResponseEntity<>(new Message(), HttpStatus.OK);
    }

}
