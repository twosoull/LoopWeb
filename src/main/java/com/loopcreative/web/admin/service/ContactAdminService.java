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

    /**
     * 1. Contact와 files를 contact_no와 join하여 리스트 반환
     * 2. List가 null 일 경우 UserErrorCode.NO_RESULT 예외 반환
     * @param pageable
     * @return
     */
    public Page<ContactDto> findContactAll(Pageable pageable){
        Page<Contact> findContacts = contactAdminRepository.findAllFetchFiles(pageable,"Y");
        contactAdminServiceVali.hasList(findContacts);
        return findContacts.map(c -> new ContactDto(c));

    }

    /**
     * 1. Contact 상세 반환
     * 2. Contact를 찾을 수 없을 시 UserErrorCode.NO_RESULT 반환
     * @param id
     * @return
     */
    public Contact findContactId(Long id){
        return contactAdminRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT));
    }

    /**
     * 1. Contact 삭제
     * 2. 삭제 아닌 UseYn = "N" 처리
     * 3. 삭제 전에 상세 찾을 수 없을 경우 UserErrorCode.NO_RESULT 반환
     * @param id
     * @return
     */
    @Transactional
    public Long delete(Long id) {
        Contact contact = contactAdminRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT));
        contact.setUseYn("N");

        return id;
    }
}
