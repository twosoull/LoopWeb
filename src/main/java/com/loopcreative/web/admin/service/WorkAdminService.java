package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.WorkAdminRepository;
import com.loopcreative.web.admin.validate.WorkAdminServiceVali;
import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.file.service.FileService;
import com.loopcreative.web.form.CreditsForm;
import com.loopcreative.web.form.WorkForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkAdminService {

    private final WorkAdminRepository workAdminRepository;
    private final WorkAdminServiceVali workAdminServiceVali;
    private final FileService fileService;

    public Page<WorkDto> findWorkAll(Pageable pageable){
        Page<Work> works = workAdminRepository.findAll(pageable);

        workAdminServiceVali.hasList(works);
        return works.map(w -> new WorkDto(w));
    }

    public WorkDto findWorkFileById(Long id){
        String str = "thumb_nail";
        return workAdminRepository.findWorkFileById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT)).changeDto();
    }

    @Transactional
    public WorkDto save(WorkForm workForm, CreditsForm creditsForm) {
        workForm.setUseYn("Y");
        Work work = workForm.toEntity();

        Work saveWork = workAdminRepository.save(work);

        //비디오

        //크레딧
        creditsForm.toEntity();



        for (Long fileId : workForm.getFileId()) {
            fileService.filesWorkIdUpdate(saveWork.getId(),fileId);
        }
        WorkDto workDto = saveWork.changeDto();
        return workDto;
    }
}
