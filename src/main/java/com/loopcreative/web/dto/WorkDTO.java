package com.loopcreative.web.dto;

import com.loopcreative.web.entity.Files;
import com.loopcreative.web.entity.Work;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class WorkDTO {

    private Long id;

    private List<Files> files = new ArrayList();

    private String workTitle;
    private String workType;
    private String useYn;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;

    public static WorkDTO toDTO(Work work){
        return WorkDTO.builder()
                .id(work.getId())
                .regDt(work.getRegDt())
                .udpDt(work.getUdpDt())
                .useYn(work.getUseYn())
                .workTitle(work.getWorkTitle())
                .workType(work.getWorkType())
                .build();
    }

}
