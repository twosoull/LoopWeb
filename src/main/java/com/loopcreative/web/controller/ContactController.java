package com.loopcreative.web.controller;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.form.ContactForm;
import com.loopcreative.web.service.ContactService;
import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact/save")
    private ResponseEntity<Message> save(ContactForm contactForm){
        Contact contact = contactService.save(contactForm);

        Message message = new Message(contact);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}
