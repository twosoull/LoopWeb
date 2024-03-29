package com.loopcreative.web.dto;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.entity.Files;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class ContactDto {

    private Long contact_no;

    private Files files;

    private String contactClientCompany;
    private String contactClientName;
    private String contactClientTel;
    private String contactClientMail;
    private String contactProjectTitle;
    private String contactProjectSchedule;
    private String contactVideoLength;
    private String contactBudget;
    private String useYn;
    private String contactContent;
    private String orgFileName;
    private String saveFileName;
    private LocalDateTime regDate;

    public ContactDto(Contact c){
        this.contact_no = c.getId();
        this.files = c.getFiles();
        this.contactClientCompany = c.getContactClientCompany();
        this.contactClientName = c.getContactClientName();
        this.contactClientTel = c.getContactClientTel();
        this.contactClientMail = c.getContactClientMail();
        this.contactProjectTitle = c.getContactProjectTitle();
        this.contactProjectSchedule = c.getContactProjectSchedule();
        this.contactVideoLength = c.getContactVideoLength();
        this.contactBudget = c.getContactBudget();
        this.contactContent = c.getContactContent();

        this.orgFileName = c.getFiles() != null ? c.getFiles().getOrgName() : "";
        this.saveFileName = c.getFiles() != null ? c.getFiles().getSaveName() : "";
        this.useYn = c.getUseYn();
        this.regDate = c.getRegDate();
    }

}
