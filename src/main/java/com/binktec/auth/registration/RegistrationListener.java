package com.binktec.auth.registration;

import com.binktec.auth.model.Users;
import com.binktec.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private IUserService service;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws MessagingException {
        Users user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                =  "http://localhost:8080//register/confirm?token=" + token;
        String message = "<html><body><a href "+confirmationUrl+">"+confirmationUrl+"</a></body></html>";

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message1 = new MimeMessageHelper(mimeMessage);
            message1.setTo(recipientAddress);
            String text = "<html><body><a href="+confirmationUrl+">"+confirmationUrl+"</a></body></html>";
            message1.setText(text, true);
        };
        this.mailSender.send(preparator);
    }
}