//package com.app.tracko.controller;
//
//import com.app.tracko.service.EmailMessage;
//import com.app.tracko.service.EmailSenderService;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class EmailController {
//
//    private EmailSenderService emailSenderService;
//
//    public EmailController(EmailSenderService emailSenderService) {
//        this.emailSenderService = emailSenderService;
//    }
//
//    @PostMapping("/send-email")
//    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage){
//        this.emailSenderService.sendEmail(
//                emailMessage.getToEmail(),
//                emailMessage.getSubject(),
//                emailMessage.getBody());
//        return ResponseEntity.ok("success");
//    }
//}
