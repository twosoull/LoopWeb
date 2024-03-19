package com.loopcreative.web.front.service;

import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.front.repository.MainRepository;
import com.loopcreative.web.entity.Work;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainService {

    final private MainRepository mainRepository;


}
