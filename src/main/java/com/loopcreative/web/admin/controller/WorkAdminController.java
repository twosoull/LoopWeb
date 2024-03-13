package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.service.WorkAdminService;
import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.entity.Files;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.file.repository.FileRepository;
import com.loopcreative.web.util.Message;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WorkAdminController {

    private final EntityManager em;

    private final WorkAdminService workAdminService;
    private final FileRepository fileRepository;
    //리스트
    @GetMapping("/admin/work/list")
    public ResponseEntity<Message> list(@PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.ASC)
                                                    Pageable pageable){
        Page<WorkDto> works = workAdminService.findWorkAll(pageable);

        Message message = new Message(works);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //조회
    @GetMapping("/admin/work/findId")
    public ResponseEntity<Message> findWorkId(){



        Message message = new Message();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //등록

    //수정

    //삭제

    //순서변경

    /*
    @PostConstruct
    @Transactional
    public void init(){
    log.info("ddafsa");
        //전체순서, 사진순서, 템플릿 타입

        for(int i = 1; i <13; i++){
            for(int j = 1; j<6; j++){
                for(int k = 1; k<5; k++ ){
                    Files file = new Files(j, k, i);
                    fileRepository.save(file);
                }
            }
        }
    }

     */

}
