package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.repository.WorkAdminRepository;
import com.loopcreative.web.admin.service.WorkAdminService;
import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.entity.*;
import com.loopcreative.web.file.repository.FileRepository;
import com.loopcreative.web.form.CreditsForm;
import com.loopcreative.web.form.VideoForm;
import com.loopcreative.web.form.WorkForm;
import com.loopcreative.web.util.Message;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WorkAdminController {

    private final EntityManager em;

    private final WorkAdminService workAdminService;
    private final FileRepository fileRepository;
    private final WorkAdminRepository workAdminRepository;
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
    public ResponseEntity<Message> findWorkId(@RequestParam("workId")Long id){
        WorkDto workDto = workAdminService.findWorkFileById(id);

        Message message = new Message(workDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //등록

    @PostMapping("/admin/work/save")
    public ResponseEntity<Message> save(WorkForm workForm, CreditsForm creditsForm, VideoForm videoForm){ //valid 처리 필요
        WorkDto workDto = workAdminService.save(workForm, creditsForm, videoForm);

        Message message = new Message(workDto);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    //수정
    @PostMapping("/admin/work/update")
    public ResponseEntity<Message> update(WorkForm workForm, CreditsForm creditsForm, VideoForm videoForm){ //valid 처리 필요
        WorkDto workDto = workAdminService.update(workForm, creditsForm, videoForm);

        Message message = new Message(workDto);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


    //삭제

    //순서변경
    //@PostConstruct
    @Transactional
    public void init(){
        Work work = new Work();
        work.setWorkTitle("asd");
        work.setWorkType("01");
        work.setUseYn("Y");

        Credits credits1 = new Credits();
        credits1.setJob("디2");
        credits1.setOrd(1);
        credits1.setName("동문");

        Credits credits2 = new Credits();
        credits2.setJob("디1");
        credits2.setOrd(2);
        credits2.setName("동문");

        Video video1 = new Video();
        video1.setVideoTitle("타이틀");
        video1.setVideoUrl("url");
        video1.setVideoType("");
        video1.setOrd(1);
        video1.setVideoContent("내용");

        Video video2 = new Video();
        video2.setVideoTitle("타이틀");
        video2.setVideoUrl("url");
        video2.setVideoType("");
        video2.setOrd(1);
        video2.setVideoContent("내용");

        work.addCredits(credits1);
        work.addCredits(credits2);
        work.addVideo(video1);
        work.addVideo(video2);

        workAdminRepository.save(work);

    }

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
