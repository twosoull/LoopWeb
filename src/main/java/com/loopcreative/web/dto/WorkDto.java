package com.loopcreative.web.dto;

import com.loopcreative.web.entity.Work;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkDto {
    private Long id;

    private String workTitle;
    private String workType;
    private String useYn;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;

    public WorkDto(Work w) {
        this.id = w.getId();
        this.workTitle = w.getWorkTitle();
        this.workType = w.getWorkType();
        this.useYn = w.getUseYn();
        this.regDt = w.getRegDate();
        this.udpDt = w.getUpdDate();
    }
}
