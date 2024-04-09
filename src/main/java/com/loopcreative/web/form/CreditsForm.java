package com.loopcreative.web.form;

import com.loopcreative.web.entity.BaseEntity;
import com.loopcreative.web.entity.Credits;
import com.loopcreative.web.entity.Work;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CreditsForm extends BaseEntity {

    //private Long[] creditsId;
    //private String[] creditsJob;
    //private String[] creditsName;
    //private Integer[] creditsOrd;

    private Long id;
    private String job;
    private String name;
    private Integer creditsOrd;

    private List<CreditsForm> creditsForms;
}
