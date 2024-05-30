package com.caisse.service.IService;

import com.caisse.entity.Client;
import com.caisse.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ClientRepository clientRepository;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            logger.info("Email sent to: {}", to);
        } catch (Exception e) {
            logger.error("Error sending email to {}: {}", to, e.getMessage(), e);
        }
    }

    public void sendBulkMessages(String subject, String text) {
        try {
            List<Client> clients = clientRepository.findAll();
            for (Client client : clients) {
                if (client.getMail() != null && !client.getMail().isEmpty()) {
                    sendSimpleMessage(client.getMail(), subject, text);
                }
            }
        } catch (Exception e) {
            logger.error("Error sending bulk emails: {}", e.getMessage(), e);
        }
    }
}
