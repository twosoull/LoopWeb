package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.WorkRepository;
import com.loopcreative.web.entity.Work;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    final private WorkRepository workRepository;

    public List<Work> list(){

        return workRepository.findAll();
    }
}
