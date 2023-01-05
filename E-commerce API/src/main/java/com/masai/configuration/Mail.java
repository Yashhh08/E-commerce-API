package com.masai.configuration;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;

@Service
public class Mail {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body){

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        javaMailSender.send(mail);

    }

}
