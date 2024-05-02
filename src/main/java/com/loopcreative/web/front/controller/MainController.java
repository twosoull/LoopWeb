package com.loopcreative.web.front.controller;

import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.front.service.MainService;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.front.service.WorkService;
import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    final private MainService mainService;
    final private WorkService workService;

    /**
     * 1. 메인은 Work를 20개씩 보여지게끔 되어있다.
     * @return
     */
    @GetMapping("/main")
    public ResponseEntity<Message> Main(){
        List<WorkDto> workDtos = workService.findFirst20ByUseYnAndFilesCdOrderByIdDesc();

        Message message = new Message(workDtos);
        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

}
