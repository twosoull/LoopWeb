package com.loopcreative.web.front.service;

import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.entity.Files;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.file.repository.FileRepository;
import com.loopcreative.web.front.repository.WorkRepository;
import com.loopcreative.web.front.validate.WorkServiceVali;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkServiceVali workServiceVali;
    private final FileRepository fileRepository;

    public List<WorkDto> findFirst20ByUseYnAndFilesCdOrderByIdDesc(){
        List<Work> works = workRepository.findWorkFilesFirst20ByUseYnAndFilesCdOrderByIdDesc("Y","thumb_nail");
        workServiceVali.hasList(works);

        List<WorkDto> collect = works.stream().map(w -> new WorkDto(w)).collect(Collectors.toList());

        return collect;
    }

    public List<WorkDto> findAllByUseYnAndFilesCdOrderByIdDesc(){
        List<Work> works = workRepository.findAllByUseYnAndFilesCdOrderByIdDesc("Y","thumb_nail");
        workServiceVali.hasList(works);

        List<WorkDto> collect = works.stream().map(w -> new WorkDto(w)).collect(Collectors.toList());

        return collect;
    }

    public WorkDto findWorkFileById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT)).changeDto();

    }
}
