package com.loopcreative.web.front.controller;

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
     *
     * @param contactForm
     * @return
     */
    @PostMapping("/contact/save")
    public ResponseEntity<Message> save(@Valid ContactForm contactForm){
        Contact saveContact = contactService.save(contactForm);

        String cd = "contact_file";
        Files files = fileUtil.uploadFile(contactForm.getMultipartFile()); //파일이 없을 경우 null 반환
        if(files != null){
            fileService.save(files,saveContact.getId(),cd);
        }

        //메일 보내는 로직 필요

        Message message = new Message();
        message.setMessage("저장에 성공했습니다.");
        message.setData(saveContact);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    /**
     *
     * @param multipartFile
     * @return
     */
    /*
    @PostMapping("/contact/fileUpload")
    public ResponseEntity<Message> insertFile(@RequestPart(value = "multipartFile") MultipartFile multipartFile){
        String cd = "contact_file";

        Files files = fileUtil.uploadFile(contactForm.getMultipartFile());
        files.setCd(cd);
        Files saveFile = fileService.save(multipartFile);

        Message message = new Message();
        message.setMessage("저장에 성공했습니다.");
        message.setData(saveFile.getId());
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
*/
}
