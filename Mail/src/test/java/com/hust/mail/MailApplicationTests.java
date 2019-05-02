package com.hust.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {

    @Resource
    MailService mailService;

    @Test
    public void sendHtmlMainTest() throws MessagingException {
        String content = "<html>\n" +
                "<body>\n" +
                "<h3> Hello World</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("574052658@qq.com", "HTML 邮件", content);
    }

    @Test
    public void sendAttachedmentsMainTest() throws MessagingException {
        String filePath = "C:/Users/menglong/Desktop/Test.txt";
        mailService.sendAttachedmentsMail("574052658@qq.com", "这是一封带附件的邮件",
                "这是一封带附件的邮件的内容", filePath);
    }

    @Test
    public void sendInLineResourceMailTest() throws MessagingException {
        String imgPath = "C:\\Users\\menglong\\Desktop\\Fig6A.jpg";
        String rscId = "01";
        String content = "<html><body> 这是有照片的邮件：<img src=\'cid:" + rscId
                + "\'> </img></body></html>";

        mailService.sendInLineResourceMail("574052658@qq.com", "带有图片的文件",
                content, imgPath, rscId);
    }

}
