package com.loopcreative.web.dto;

import com.loopcreative.web.entity.Credits;
import com.loopcreative.web.entity.Work;
import lombok.Data;

@Data
public class CreditsDto {

    private Long id;
    private String job;
    private String name;
    private Integer ord;

    public CreditsDto(Credits c) {
        this.id = c.getId();
        this.job = c.getJob();
        this.name = c.getName();
        this.ord = c.getOrd();
    }


}
