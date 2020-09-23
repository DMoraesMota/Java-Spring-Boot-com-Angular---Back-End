package com.diegomota.curso.ws.services.email;

import com.diegomota.curso.ws.domain.User;
import com.diegomota.curso.ws.domain.VerificationToken;
import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendHtmlEmail(MimeMessage msg);
    void sendConfirmationHtmlEmail(User user, VerificationToken vToken);
}
