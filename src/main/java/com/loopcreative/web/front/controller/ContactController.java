package com.loopcreative.web.front.controller;

import com.loopcreative.web.dto.ContactDto;
import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.entity.Files;
import com.loopcreative.web.file.service.FileService;
import com.loopcreative.web.form.ContactForm;
import com.loopcreative.web.front.service.ContactService;
import com.loopcreative.web.util.FileUtil;
import com.loopcreative.web.util.Message;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;
    private final FileService fileService;
    private final FileUtil fileUtil;

    /**
     * 1. Contact 저장 및 Files 한번에 저장
     * 2. 파일 저장 시에 등록된 관리자의 메일로 메일이 넘어간다.(파일 함께)
     * @param contactForm
     * @return
     */
    @PostMapping("/contact/save")
    public ResponseEntity<Message> save(@Valid ContactForm contactForm){
        Contact saveContact = contactService.save(contactForm);
        ContactDto contactDto = new ContactDto(saveContact);
        String cd = "contact_file";
        Files files = fileUtil.uploadFile(contactForm.getMultipartFile()); //파일이 없을 경우 null 반환
        if(files != null){
            fileService.save(files,cd);
            fileService.filesContactIdUpdate(saveContact.getId(), files.getId(), cd);
        }

        //메일 보내는 로직 필요

        Message message = new Message();
        message.setMessage("저장에 성공했습니다.");
        message.setData(contactDto);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

}
