package com.loopcreative.web.admin.controller;

import com.loopcreative.web.admin.service.ContactAdminService;
import com.loopcreative.web.dto.ContactDto;
import com.loopcreative.web.entity.Contact;

import com.loopcreative.web.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContactAdminController {

    final private ContactAdminService contactAdminService;

    /**
     * 1. Message.data = Contact List 반환
     * @param pageable
     * @return ResponseEntity<Message>
     */
    @GetMapping("/admin/contact/list")
    public ResponseEntity<Message> list(@PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.ASC)
                              Pageable pageable){
        Page<ContactDto> contacts = contactAdminService.findContactAll(pageable);

        Message message = new Message(contacts);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    /**
     * 1. Message.data = Contact 반환
     * @param id
     * @return ResponseEntity<Message>
     */
    @GetMapping("/admin/contact/findId")
    public ResponseEntity<Message> findContactId(@RequestParam(name = "contactId") Long id){
        Contact findContact= contactAdminService.findContactId(id);
        Message message = new Message(findContact);

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    /**
     * 1. Message.data = Contact.Id 반환
     * @param id
     * @return ResponseEntity<Message>
     */
    @PostMapping("/admin/contact/delete")
    public ResponseEntity<Message> delete(@RequestParam(name = "contactId") Long id){
        Long deleteId = contactAdminService.delete(id);
        Message message = new Message(deleteId);

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

}
