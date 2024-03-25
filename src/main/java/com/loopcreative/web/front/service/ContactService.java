package com.loopcreative.web.front.service;

import com.loopcreative.web.entity.Contact;
import com.loopcreative.web.entity.Files;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.form.ContactForm;
import com.loopcreative.web.front.repository.ContactRepository;
import com.loopcreative.web.front.validate.ContactServiceVali;
import com.loopcreative.web.util.MailUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactServiceVali contactServiceVali;
    private final JavaMailSenderImpl javaMailSender;
    private final MailUtil mailUtil;

    @Value("${file.server.path}")
    private String fileServerPath;
    /**
     * 1. valid 후 Contact 저장
     * @param contactForm
     * @return
     */
    @Transactional
    public Contact save(ContactForm contactForm){
        contactServiceVali.saveValidation(contactForm);
        Contact contact = contactForm.toEntity(contactForm);
        Contact saveContact = contactRepository.save(contact);
        return saveContact;
    }

    /**
     * 1. 홈페이지 방문자가 contact를 작성할 경우 관리자의 메일로 견적서가 발송된다.
     * @param contactForm, files
     */
    public void sendMailContact(ContactForm contactForm, Files files) throws MessagingException {

        String subject = "["+contactForm.getContactClientCompany()+"] " + contactForm.getContactProjectTitle();
        String fileFullPath = fileServerPath + files.getFilePath();
        String content = makeHtml(contactForm);

        mailUtil.sendMail(contactForm.getContactClientMail(),fileFullPath,subject,content);
    }

    /**
     * 1. 프로젝트에 요청사항, 클라이언트 정보 메일 작성 form
     * @param contactForm
     * @return
     */
    public String makeHtml(ContactForm contactForm) {
        String contactClientCompany = "contactClientCompany";
        String contactClientName = "contactClientName";
        String contactClientTel = "contactClientTel";
        String contactClientMail = "contactClientMail";
        String contactClientTitle = "contactClientTitle";
        String contactProjectSchedule = "contactProjectSchedule";
        String contactVideoLength = "contactVideoLength";
        String contactBudget = "contactBudget";
        String contactContent = "asfas";

        String content = "<!DOCTYPE html>\r\n"
                + "<html lang=\"en\">\r\n"
                + "<head>\r\n"
                + "    <meta charset=\"UTF-8\">\r\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                + "    <title>Document</title>\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "    <section style=\"width:640px; height: 676px; font-weight: 100; margin:0 auto; letter-spacing: -0.25px; font-family: 'Noto Sans'; line-height: 1.4 \">\r\n"
                + "        <div style=\"width:540px; height: 576px; display:inline-block; margin:50px 50px 50px 50px;\">\r\n"
                + "            <div style=\"width:540px; height: 100px; background-color: white\" >\r\n"
                + "                <div style=\"margin:27px 0px 0px 0px;\">\r\n"
                + "                  <p style=\"margin:0px; font-size: 15px; font-weight: 500; color:#555\">루프모션 스튜디오</p>\r\n"
                + "                <p style=\"margin:5px 0px 0px 0px; font-size: 24px; font-weight: 600; color:#333\">새로운 제안서가 도착했습니다.</p>\r\n"
                + "                </div>\r\n"
                + "            </div>\r\n"
                + "            <div style=\"width:540px; height: 370px; border-top: 1.4px solid #D9D9D9; border-bottom: 1.4px solid #D9D9D9;\">\r\n"
                + "                <div style=\"font-size: 14px; margin: 51px 0px 0px 0px; color:#555\">\r\n"
                + "                    <p style=\"margin:0px;\">안녕하세요. <span style=\"font-weight: bold;\">제안서 안내드립니다.</span></p>\r\n"
                + "                    <br>\r\n"
                + "                    <p style=\"margin:0px;\">회사명 : " + contactClientCompany + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">성함 : " + contactClientName + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">연락처 : " + contactClientTel + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">고객사 메일 : " + contactClientMail + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">프로젝트명 : " + contactClientTitle + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">제작일정 : " + contactProjectSchedule + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">영상길이 : " + contactVideoLength + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">제작예산 : " + contactBudget + "</p>\r\n"
                + "                    <p style=\"margin:0px;\">상담내용 등 참고할 레퍼런스 : " + contactContent + "</p>\r\n"
                + "                </div>\r\n"
                + "                <div style=\"width:100%; height: 140px;\">\r\n"
                + "                    <div style=\"display: inline-block; margin: 50px 180px 50px 180px\">\r\n"
                + "                    </div>\r\n"
                + "                </div>\r\n"
                + "            </div>\r\n"
                + "        </div>\r\n"
                + "    </section>\r\n"
                + "</body>\r\n"
                + "</html>";

        return content;

    }

}
