//package com.app.tracko.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailSenderService {
//    private final JavaMailSender mailSender;
//
//    public EmailSenderService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//
//    public String sendEmail( String toEmail, String body , String subject){
//        try{
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//
//        simpleMailMessage.setFrom("tracko1app@gmail.com");
//        simpleMailMessage.setTo(toEmail);
//        simpleMailMessage.setText(body);
//        simpleMailMessage.setSubject(subject);
//
//        this.mailSender.send(simpleMailMessage);
//        return "Mail Sent Successfully...";
//
//    }
//    catch(Exception e) {
//        return "Error while Sending Mail";
//    }
//    }
//
//
//}
