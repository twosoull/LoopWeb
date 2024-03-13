package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.ContactAdminRepository;
import com.loopcreative.web.admin.validate.ContactAdminServiceVali;
import com.loopcreative.web.dto.ContactDto;
import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactAdminService {

    private final ContactAdminRepository contactAdminRepository;
    private final ContactAdminServiceVali contactAdminServiceVali;

    public Page<ContactDto> findContactAll(Pageable pageable){
        Page<Contact> findContacts = contactAdminRepository.findAllFetchFiles(pageable,"Y");
        contactAdminServiceVali.hasList(findContacts);
        return findContacts.map(c -> new ContactDto(c));

    }

    public Contact findContactId(Long id){
        return contactAdminRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT));
    }

    @Transactional
    public Long delete(Long id) {
        Contact contact = contactAdminRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT));
        contact.setUseYn("N");

        return id;
    }
}
