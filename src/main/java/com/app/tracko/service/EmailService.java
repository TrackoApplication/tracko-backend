//package com.app.tracko.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendVerificationEmail(String name, String email, String verificationToken) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(email);
//        mailMessage.setSubject("Verify your account");
//        mailMessage.setText("Hello " + name + ",\n\n"
//                + "Thank you for registering with us! Please click the link below to verify your account:\n\n"
//                + "http://localhost:8080/verify?token=" + verificationToken + "\n\n"
//                + "Best regards,\n"
//                + "Your Name");
//
//        javaMailSender.send(mailMessage);
//    }
//
//
//
//}
