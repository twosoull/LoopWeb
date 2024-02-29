package com.loopcreative.web.controller;

import com.loopcreative.web.dto.WorkDTO;
import com.loopcreative.web.service.MainService;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    final private MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<Message> Main(){

        List<Work> works = mainService.mainWorkList();

        List<WorkDTO> workDTOList = new ArrayList<>();
        for (Work w : works) {
            workDTOList.add(WorkDTO.toDTO(w));
        }

        Message message = Message.getMessage(workDTOList,HttpStatus.OK);

        return new ResponseEntity<Message>(message,HttpStatus.OK);
    }

}
