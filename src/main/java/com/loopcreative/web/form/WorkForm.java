package com.loopcreative.web.form;

import com.loopcreative.web.entity.Work;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkForm {

    private String workTitle;
    private String workType;
    private String useYn;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
    private Long[] fileId;

    public Work toEntity(){
        Work work = new Work();
        work.setWorkTitle(this.workTitle);
        work.setWorkType(this.workType);
        work.setUseYn(this.useYn);

        return work;
    }
}
