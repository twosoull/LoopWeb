package com.loopcreative.web.front.service;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.form.ContactForm;
import com.loopcreative.web.front.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public void save(ContactForm contactForm){
        contactForm.setUseYn("Y");
        Contact contact = contactForm.changeEntity(contactForm);
        contactRepository.save(contact);
    }
}
