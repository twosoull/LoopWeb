package com.loopcreative.web.form;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.entity.Files;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactForm {
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
    private LocalDate regDate;

    public Contact changeEntity(ContactForm cf){
        Contact c = new Contact();
        c.setContactClientName(cf.getContactClientName());
        c.setContactClientCompany(cf.getContactClientCompany());
        c.setContactClientTel(cf.getContactClientTel());
        c.setContactBudget(cf.getContactBudget());
        c.setUseYn("Y");
        c.setContactClientMail(cf.getContactClientMail());
        c.setContactProjectSchedule(cf.getContactProjectSchedule());
        c.setContactVideoLength(cf.getContactVideoLength());
        c.setContactProjectTitle(cf.contactProjectTitle);
        c.setFiles(cf.getFiles());

        return c;
    }
}
