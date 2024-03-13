package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.WorkAdminRepository;
import com.loopcreative.web.admin.validate.WorkAdminServiceVali;
import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkAdminService {

    private final WorkAdminRepository workAdminRepository;
    private final WorkAdminServiceVali workAdminServiceVali;

    public Page<WorkDto> findWorkAll(Pageable pageable){
        Page<Work> works = workAdminRepository.findAll(pageable);

        workAdminServiceVali.hasList(works);
        return works.map(w -> new WorkDto(w));
    }

    public Work findWorkId(Long id){
        return workAdminRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT));
    }

}
