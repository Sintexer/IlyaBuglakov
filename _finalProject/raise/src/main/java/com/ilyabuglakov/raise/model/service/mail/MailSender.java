package com.ilyabuglakov.raise.model.service.mail;

import javax.mail.MessagingException;

public interface MailSender {
    void sendMail(String email, String title, String messageBody) throws MessagingException;
}
