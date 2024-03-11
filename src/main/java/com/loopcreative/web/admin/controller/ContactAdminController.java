package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.service.ContactAdminService;
import com.loopcreative.web.dto.ContactDto;
import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.util.Message;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    @ExceptionHandler
    @GetMapping("/admin/contact/list")
    public ResponseEntity<Message> list(@PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.ASC)
                              Pageable pageable){
        //1. 목록 가져오기 (페이지 넘버도 가져오기)
        Page<Contact> findContacts = contactAdminService.findAll(pageable);
        Page<ContactDto> contacts = findContacts.map(c -> new ContactDto(c));
        //2. Message에 담는다.
        Message message = new Message(contacts);

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }


    //조회

    //수정

    //삭제


}
