package com.loopcreative.web.form;

import com.loopcreative.web.entity.Credits;
import com.loopcreative.web.entity.Work;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CreditsForm {

    private Long id;
    private String job;
    private String name;
    private Integer ord;

    public void toEntity() {
        Credits c = new Credits();
        c.setJob(this.job);
        c.setName(this.name);
        c.setOrd(this.ord);
    }
}
