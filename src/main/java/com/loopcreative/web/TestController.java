package com.loopcreative.web;

import com.loopcreative.web.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/test")
    public String test(){

        Contact contact = em.find(Contact.class, 140);

        return contact.getFiles().toString();
    }
}
