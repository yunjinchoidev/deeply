package com.server.deeply.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(){

        String toUser = "cyj7157@naver.com";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toUser);
        simpleMailMessage.setSubject("안녕하세요?");
        simpleMailMessage.setText("안녕하세요. 디플리에 오신 여러분을 환영합니다");
        javaMailSender.send(simpleMailMessage);

    }
}
