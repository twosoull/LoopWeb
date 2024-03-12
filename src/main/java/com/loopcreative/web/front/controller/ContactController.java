package com.loopcreative.web.front.controller;

import com.loopcreative.web.entity.Files;
import com.loopcreative.web.file.repository.FileRepository;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;
    private final FileService fileService;
    /**
     *
     * @param contactForm
     * @return
     */
    @PostMapping("/contact/save")
    public ResponseEntity<Message> save(@Valid ContactForm contactForm){
        contactService.save(contactForm);

        return new ResponseEntity<Message>(new Message(), HttpStatus.OK);
    }

    /**
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/contact/fileUpload")
    public ResponseEntity<Message> insertFile(@RequestPart(value = "multipartFile") MultipartFile multipartFile){
        String cd = "contact_file";
        Files saveFile = fileService.save(multipartFile, cd);

        Message message = new Message();
        message.setMessage("저장에 성공했습니다.");
        message.setData(saveFile.getId());
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

}
