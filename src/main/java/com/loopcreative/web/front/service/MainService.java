package com.loopcreative.web.front.service;

import com.loopcreative.web.front.repository.MainRepository;
import com.loopcreative.web.entity.Work;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    final private MainRepository mainRepository;

    public List<Work> mainWorkList(){
        return mainRepository.findWorks();
    }
}
