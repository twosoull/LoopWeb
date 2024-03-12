package com.loopcreative.web.front.controller;

import com.loopcreative.web.front.service.MainService;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    final private MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<Message> Main(){

        List<Work> works = mainService.mainWorkList();

        //works.stream().map(w -> new WorkDTO(w));

        //Message message = Message.getMessage(workDTOList,HttpStatus.OK);

        // return new ResponseEntity<Message>(message,HttpStatus.OK);

        return null;
    }

}
