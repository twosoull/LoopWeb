package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.ContactAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactAdminService {

    final private ContactAdminRepository contactAdminRepository;


}
