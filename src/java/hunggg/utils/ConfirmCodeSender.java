/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.utils;

import java.util.Properties;
import java.util.Random;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Hung
 */
public class ConfirmCodeSender {

    private static final Logger LOGGER = Logger.getLogger(ConfirmCodeSender.class);

    public int sendConfirmationCode(String receiverEmail) {

        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));

        Random random = new Random();
        final String username = "hungggse140348@fpt.edu.vn";
        final String password = "xjikhesmfoyzuqmr";
        final int confirmCode = random.nextInt(2147483647);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail)
            );
            message.setSubject("Account activiation: ");

            message.setText("If you are " + receiverEmail
                    + " then enter code " + confirmCode + "\n"
                    + " else , do not read this ");

            Transport.send(message);
            
        } catch (MessagingException e) {
            LOGGER.error("ConfirmationCodeSender Messaging Exception: " + e.getMessage());
        }
        return confirmCode;
    }
}
