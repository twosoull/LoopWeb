package com.loopcreative.web.util;

import com.loopcreative.web.entity.Files;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.form.ContactForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@RequiredArgsConstructor
@Component
public class MailUtil {

    private final JavaMailSenderImpl javaMailSender;

    @Value("${file.server.path}")
    private String fileServerPath;

    /**
     * 1. 메일 보내기
     * @param   fromMail, filePath, subject, content
     */
    public void sendMail(String fromMail, String filePath, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            // Use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("loopcreative.corp@gmail.com");
            helper.setFrom(fromMail);
            helper.setSubject(subject);

            // Set HTML content
            helper.setText(content, true);

            // Add file attachment
            if(!"".equals(filePath)){
                File file = new File(filePath);
                helper.addAttachment(file.getName(), file);
            }
        } catch (MessagingException e) {
            new RestApiException(UserErrorCode.FAIL_MAIL_SEND);
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }
}
