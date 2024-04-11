package com.loopcreative.web.form;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.entity.Files;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class ContactForm {
    private Long contact_no;

    private Files files;
    private MultipartFile multipartFile; /*첨부파일*/
    @NotBlank(message = "고객 회사명을 입력해주세요.")
    private String contactClientCompany;
    @NotBlank(message = "고객 이름을 입력해주세요.")
    private String contactClientName;
    @NotBlank(message = "고객 전화번호를 입력해주세요.")
    private String contactClientTel;
    @NotBlank(message = "고객 메일을 입력해주세요.")
    private String contactClientMail;
    @NotBlank(message = "프로젝트 명을 입력해주세요.")
    private String contactProjectTitle;
    @NotBlank(message = "프로젝트 제작 일정을 입력해주세요.")
    private String contactProjectSchedule;
    @NotBlank(message = "영상 길이를 확인해주세요.")
    private String contactVideoLength;
    @NotBlank(message = "제작 예산을 확인해주세요.")
    private String contactBudget;

    private String contactContent;

    private String useYn;
    private LocalDate regDate;

    public Contact toEntity(ContactForm cf){
        Contact c = new Contact();
        c.setContactClientName(cf.getContactClientName());
        c.setContactClientCompany(cf.getContactClientCompany());
        c.setContactClientTel(cf.getContactClientTel());
        c.setContactBudget(cf.getContactBudget());
        c.setUseYn(cf.getUseYn());
        c.setContactClientMail(cf.getContactClientMail());
        c.setContactProjectSchedule(cf.getContactProjectSchedule());
        c.setContactVideoLength(cf.getContactVideoLength());
        c.setContactProjectTitle(cf.getContactProjectTitle());
        c.setFiles(cf.getFiles());
        c.setContactContent(cf.getContactContent());
        return c;
    }

}
