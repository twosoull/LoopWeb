package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.repository.WorkAdminRepository;
import com.loopcreative.web.admin.service.WorkAdminService;
import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.entity.*;
import com.loopcreative.web.file.repository.FileRepository;
import com.loopcreative.web.form.CreditsForm;
import com.loopcreative.web.form.VideoForm;
import com.loopcreative.web.form.WorkForm;
import com.loopcreative.web.form.WorkSaveRequest;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkAdminController {

    private final EntityManager em;

    private final WorkAdminService workAdminService;
    private final FileRepository fileRepository;
    private final WorkAdminRepository workAdminRepository;

    /**
     * 1. Work 리스트 페이징 처리하여 반환
     * 2. 한 페이지당 개수는 10개 지정
     * 3. regData 최신 순으로 처리
     * @param pageable
     * @return
     */
    @GetMapping("/admin/work/list")
    public ResponseEntity<Message> list(@PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.DESC)
                                                    Pageable pageable){
        Page<WorkDto> works = workAdminService.findWorkAll(pageable);

        Message message = new Message(works);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 1. id 값으로 상세 찾고 반환
     * @param id
     * @return
     */
    @GetMapping("/admin/work/findId")
    public ResponseEntity<Message> findWorkId(@RequestParam("workId")Long id){
        WorkDto workDto = workAdminService.findWorkFileById(id);

        Message message = new Message(workDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 1. Work, Credits, Video 한번에 반환
     * @param workSaveRequest
     * @return
     */
    @PostMapping("/admin/work/save")
    public ResponseEntity<Message> save(@RequestBody WorkSaveRequest workSaveRequest){
        WorkDto workDto = workAdminService.save(
                workSaveRequest.getWorkForm(),
                workSaveRequest.getCreditsForms(),
                workSaveRequest.getVideoForms(),
                workSaveRequest.getSaveFileForms(),
                workSaveRequest.getThumbnailFileForm(),
                workSaveRequest.getRemoveFileForms());

        Message message = new Message(workDto);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    /**
     * 1. Work, Credits, Video 한번에 수정
     * @param workSaveRequest
     * @return
     */
    @PostMapping("/admin/work/update")
    public ResponseEntity<Message> update(@RequestBody WorkSaveRequest workSaveRequest){ //valid 처리 필요
        WorkDto workDto = workAdminService.update(
                workSaveRequest.getWorkForm(),
                workSaveRequest.getCreditsForms(),
                workSaveRequest.getVideoForms(),
                workSaveRequest.getSaveFileForms(),
                workSaveRequest.getThumbnailFileForm(),
                workSaveRequest.getRemoveFileForms(),
                workSaveRequest.getRemoveVideoForms(),
                workSaveRequest.getRemoveCreditsForms());

        Message message = new Message(workDto);
        //Message message = new Message();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
