package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.ContactAdminRepository;
import com.loopcreative.web.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactAdminService {

    private final ContactAdminRepository contactAdminRepository;

    public Page<Contact> findAll(Pageable pageable){
        return contactAdminRepository.findAllFetchFiles(pageable);

    }

}
