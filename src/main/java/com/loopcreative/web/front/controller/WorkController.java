package com.loopcreative.web.front.controller;

import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.front.service.WorkService;
import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor//
public class WorkController {

    private final WorkService workService;

    /**
     * 1. work 페이지 - 모든 컨텐츠 다 보여주기
     * @return
     */
    @GetMapping("/work/init")
    public ResponseEntity<Message> list(){
        List<WorkDto> workDtos = workService.findAllByOrderByRegDateDesc();

        Message message = new Message(workDtos);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}
