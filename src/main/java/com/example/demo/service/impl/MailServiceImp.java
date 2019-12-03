package com.example.demo.service.impl;

import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * <p>Title:MailServiceImp.java</p >
 * <p>Description:inplements</p >
 *
 * @author Sephinor
 * @version 1.0
 * @time 2019/12/2 19:35
 */
@Service
public class MailServiceImp implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送文本邮件
     * @param to  收件人
     * @param subject 标题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setText(content);
        message.setSubject(subject);

        try {
            mailSender.send(message);
            System.out.println("---简单邮件已发送---");
        } catch (MailException e) {
            System.err.println("简单邮件发送异常,错误信息: \r\n"+e);
        }
    }

    /**
     * 发送HTML邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            System.out.println("html邮件发送成功");
        } catch (MessagingException e) {
            System.err.println("html邮件发送异常:\r\n"+e);
        }
    }

    @Override
    public void sendAttachmmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            System.out.println("带附件的邮件发送成功");
        } catch (MessagingException e) {
            System.err.println("带附件的邮件发送异常:\r\n"+e);
        }
    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            System.out.println("嵌入静态资源的邮件已发送");
        } catch (MessagingException e) {
            System.err.println("邮件发送异常 \r\n"+e);
        }

    }

    @Override
    public void sendTemplateMail(String to, String subject, String content) {

    }


}
