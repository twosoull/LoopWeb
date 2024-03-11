package com.loopcreative.web.service;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.form.ContactForm;
import com.loopcreative.web.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Contact save(ContactForm contactForm){
        Contact contact = contactForm.changeEntity(contactForm);

        Contact save = contactRepository.save(contact);
    }
}
