package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.service.ContactAdminService;
import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.util.Message;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ContactAdminController {

    final private ContactAdminService contactAdminService;

    //리스트
    /*
    @ExceptionHandler
    @GetMapping("/admin/contact/list")
    public Page<Contact> list(@PageableDefault(page = 0, size = 5, sort = "regDate", direction = Sort.Direction.ASC)
                              Pageable pageable){

        //1. 목록 가져오기 (페이지 넘버도 가져오기)
        //Page<Contact> findContacts = contactAdminService.findAll(pageable);

        //2. Message에 담는다.




        return null;
    }
*/
    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외 발생");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404 오류");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(501);
    }


    //조회

    //수정

    //삭제


}
