package com.kh.zipplanet.domain.mail.service;

import com.kh.zipplanet.domain.mail.model.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailMessageService {

    private final JavaMailSender javaMailSender;
    private static final String ADMIN_ADDRESS = "zip-planet@naver.com";
    @Async
    public void sendMail(EmailMessage emailMessage) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getTo());
        message.setFrom(ADMIN_ADDRESS);
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getMessage());
        javaMailSender.send(message);
    }
}
