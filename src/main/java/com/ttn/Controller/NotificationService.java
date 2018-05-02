package com.ttn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    private String from;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
//   Send Email In 10 Seconds automatically
//    @Scheduled(cron = "*/10 * * * * *" )
    public void sendNotificaitoin() throws MailException, InterruptedException {

        System.out.println("Sleeping now...");
        Thread.sleep(5000);

        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("sahil.14bit1114@abes.ac.in");
        mail.setFrom(from);
        mail.setSubject("radhe radhe");
        mail.setText("hare krishna");
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }
}
