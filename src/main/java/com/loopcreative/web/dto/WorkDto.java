package com.loopcreative.web.dto;

import com.loopcreative.web.entity.Credits;
import com.loopcreative.web.entity.Files;
import com.loopcreative.web.entity.Video;
import com.loopcreative.web.entity.Work;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class WorkDto {
    private Long id;

    private String workTitle;
    private String workType;
    private String useYn;
    private LocalDateTime regDt;
    private LocalDateTime udpDt;
    private List<FilesDto> filesList;
    private List<CreditsDto> credits;
    private List<VideoDto> videos;


    public WorkDto() {
    }

    public WorkDto(Work w) {
        this.id = w.getId();
        this.workTitle = w.getWorkTitle();
        this.workType = w.getWorkType();
        this.useYn = w.getUseYn();
        this.regDt = w.getRegDate();
        this.udpDt = w.getUpdDate();
    }
}
