package com.server.deeply.config.handler;

import java.io.File;
import java.io.UnsupportedEncodingException;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
    private JavaMailSender sender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    //MailHandler의 생성자
    public MailHandler(JavaMailSender jSender) throws
            MessagingException {
        this.sender = jSender;
        message = jSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        //MimeMessageHelper의 생성자 두번째 파라미터는 다수의 사람에게 보낼 수 있는 설정, 세번째는 기본 인코딩 방식
    }

    //이 이메일이 누구로부터 가는가.. 실제로 써본결과 그다지 중요하지 않은듯..잘  모르겠습니다.
    public void setFrom(String email, String name) throws
            UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email, name);
    }

    //누구에게 보낼 것인가.. 보낼사람의 이메일
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    //보낼때 제목
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    //보낼 메일의 내용.. 두번째 파라미터는 html을 적용할 것인가 아닌가. true시 html형식으로 작성하면 html형식으로 보임..
    public void setText(String text) throws MessagingException {
        messageHelper.setText(text, true);
    }

    //addInline메서드는 간단한 첨부파일을 보내는데 쓰이는 것 같습니다.(저는 아직 사용 해보지 않았습니다.)  https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch26s03.html 에 가셔서 한번 보셔도 좋을 것 같습니다.
    public void addInline(String contentId, Resource resource) throws
            MessagingException {
        messageHelper.addInline(contentId, resource);
    }

    public void addInline(String contentId, File file) throws
            MessagingException {
        messageHelper.addInline(contentId, file);
    }

    public void addInline(String contentId, DataSource source) throws
            MessagingException {
        messageHelper.addInline(contentId, source);
    }

    //실제로 메일을 보내는 메서드..
    public void send() {
        try {
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


