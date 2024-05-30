package com.caisse.controller;

import com.caisse.service.IService.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/email")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public void sendBulkEmail(@RequestBody EmailRequest emailRequest) {
        logger.info("Received request to send bulk email with subject: {}", emailRequest.getSubject());
        try {
            emailService.sendBulkMessages(emailRequest.getSubject(), emailRequest.getMessage());
            logger.info("Bulk email sent successfully");
        } catch (Exception e) {
            logger.error("Error in sending bulk email: {}", e.getMessage(), e);
        }
    }
}

class EmailRequest {
    private String subject;
    private String message;

    // Getters and setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
