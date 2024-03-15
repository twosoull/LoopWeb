package com.loopcreative.web.form;

import com.loopcreative.web.entity.Credits;
import com.loopcreative.web.entity.Work;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreditsForm {

    private Long[] creditsId;
    private String[] creditsJob;
    private String[] creditsName;
    private Integer[] creditsOrd;


}
