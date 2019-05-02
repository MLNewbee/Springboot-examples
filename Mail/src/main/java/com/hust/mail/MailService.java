package com.hust.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String From;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String To, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(To);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(From);

        mailSender.send(message);
    }

    public void sendHtmlMail(String To, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(From);
        helper.setTo(To);
        helper.setSubject(subject);
        helper.setText(content);
        mailSender.send(message);

    }

    public void sendAttachedmentsMail(String To, String subject, String content, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(From);
        helper.setTo(To);
        helper.setSubject(subject);
        helper.setText(content);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String filename = file.getFilename();
        helper.addAttachment(filename, file);
        mailSender.send(message);

    }

    public void sendInLineResourceMail(String To, String subject, String content, String rscPath, String rscId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(From);
        helper.setTo(To);
        helper.setSubject(subject);
        helper.setText(content);

        FileSystemResource resource = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, resource);
        mailSender.send(message);

    }
}
