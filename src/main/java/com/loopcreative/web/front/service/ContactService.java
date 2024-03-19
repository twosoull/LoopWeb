package com.loopcreative.web.front.service;

import com.loopcreative.web.admin.validate.ContactAdminServiceVali;
import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.form.ContactForm;
import com.loopcreative.web.front.repository.ContactRepository;
import com.loopcreative.web.front.validate.ContactServiceVali;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactServiceVali contactServiceVali;

    /**
     * 1. valid 후 Contact 저장
     * @param contactForm
     * @return
     */
    @Transactional
    public Contact save(ContactForm contactForm){
        contactServiceVali.saveValidation(contactForm);
        Contact contact = contactForm.toEntity(contactForm);
        Contact saveContact = contactRepository.save(contact);
        return saveContact;
    }
}
