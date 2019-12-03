package com.example.demo.test;

import com.example.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

/**
 * <p>Title:MailTest.java</p >
 * <p>Description:test</p >
 *
 * @author Sephinor
 * @time 2019/12/2 19:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("w229084881@vip.qq.com")
    private String to;

    @Test
    public void testSimpleMail(){
        mailService.sendSimpleMail(to,"simple mail","simple mail has sent");
    }

    @Test
    public void testHtmlMail(){
        String content="<html>\n" +
                "<body>\n" +
                "   <h3>就这样把! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(to,"html mail",content);
    }

    @Test
    public void sendAttachmentsMail(){
        String filePath="E:\\360\\123.txt";
        mailService.sendAttachmmentsMail(to,"附件邮件","Attachments,receive please ",filePath);
    }
    @Test
    public void sendInlineResourceMail(){
        String rscId = "pic001";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "E:\\360\\音视频相关话术.png";

        mailService.sendInlineResourceMail(to,"图片邮件",content,imgPath,rscId);
    }
}
