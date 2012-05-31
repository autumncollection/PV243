package inc;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/** 
 * MailClient.java
 * Purpose: Sends mails with information about registration.
 * 
 * @author Tomáš Hrabal
 * @version 1.0
 */

public class MailClient {

    /**
     * Sends mail containing information about registration to newly registered user.
     * 
     * @param to Users email.
     * @param subject Subject of message.
     * @param messageBody Body of message.
     * @throws MessagingException
     * @throws AddressException 
     */
    public void sendMail(String to, String subject, String messageBody) throws MessagingException, AddressException {
        String host = "smtp.gmail.com";
        String from = "muni.ebookseshop@gmail.com";
        String pass = "pS2XUqqgzHQ6GJd2Jx1I";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        String[] to_ = {to};

        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        InternetAddress[] toAddress = new InternetAddress[to_.length];

        // To get the array of addresses
        for (int i = 0; i < to_.length; i++) { // changed from a while loop
            toAddress[i] = new InternetAddress(to_[i]);
        }

        for (int i = 0; i < to_.length; i++) { // changed from a while loop
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }
        message.setSubject(subject);
        message.setText(messageBody);
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();


    }
}