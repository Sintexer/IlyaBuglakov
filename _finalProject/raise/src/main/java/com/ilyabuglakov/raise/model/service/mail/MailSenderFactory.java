package com.ilyabuglakov.raise.model.service.mail;

public class MailSenderFactory {

    public MailSender createMailSender(){
        return new UserMailSender();
    }

}
