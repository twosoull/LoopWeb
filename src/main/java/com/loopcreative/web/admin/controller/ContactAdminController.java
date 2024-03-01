package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.service.ContactAdminService;
import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactAdminController {

    final private ContactAdminService contactAdminService;

    public ResponseEntity<Message> list(){

        //1. 목록 가져오기 (페이지 넘버도 가져오기)

        //2. Message에 담는다.

        return null;
    }
}
