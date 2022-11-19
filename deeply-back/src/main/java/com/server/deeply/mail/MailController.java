package com.server.deeply.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail/send")
    public String mailSend(){
        mailService.sendMail();
        return "성공";
    }

}
