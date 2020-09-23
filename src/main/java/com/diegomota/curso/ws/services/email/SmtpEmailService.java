package com.diegomota.curso.ws.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;


public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Enviando E-mail");
        javaMailSender.send(msg);
        LOG.info("Email Enviado Com Sucesso");
    }
}
