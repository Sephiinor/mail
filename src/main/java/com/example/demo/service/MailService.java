package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>Title:MailService.java</p >
 * <p>Description:service</p >
 *
 * @author Sephinor
 * @time 2019/12/2 19:31
 */

public interface MailService {

    void sendSimpleMail(String to,String subject,String content);

    /**
     * html格式邮件
     */
    void sendHtmlMail(String to,String subject,String content);

    /**
     * 带附件的邮件
     * @param filePath
     */
    void sendAttachmmentsMail(String to,String subject,String content,String filePath);

    /**
     *  带静态资源的邮件
     * @param rscPath  资源路径
     * @param rscId  资源ID
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

    void sendTemplateMail(String to,String subject,String content);
}
