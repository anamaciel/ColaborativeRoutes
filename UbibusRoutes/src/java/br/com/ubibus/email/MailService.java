package br.com.ubibus.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class MailService.java has designed to send email.
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 *
 */
public class MailService {

    /**
     * Defines the message type as html
     */
    public static final String TYPE_HTML = "text/html";
    /**
     * Defines the message type as plain text
     */
    public static final String TYPE_TEXT = "text/plain";
    /**
     * Server for email sending
     */
    private String mailServer;
    /**
     * Port of email server
     */
    private String mailServerPort;
    /**
     * User email for authentication on email server
     */
    private String email = "anEmail@gmail.com";
    /**
     * User password for authentication on email server
     */
    private String password = "password";

    /**
     * Creates a new MailService object initializing the mailServer and
     * mailServerPort properties with default values for SMTP (gmail).
     */
    public MailService() {
        mailServer = "smtp.gmail.com"; // sets the mailserver as gmail
        mailServerPort = "465";// default gmail server port
    }

    /**
     * Creates a new MailService object initializing the mailServer and
     * mailServerPort properties from the parameters values.
     *
     * @param mailServer - server for email sending
     * @param mailServerPort - port of email server
     */
    public MailService(String mailServer, String mailServerPort) {
        this.mailServer = mailServer;
        this.mailServerPort = mailServerPort;
    }

    /**
     * Sent the email using the parameters values to configure the transport.
     *
     * @param from - mail address from wich will be sent the message, must be
     * GMAIL account.
     * @param to - mail to email address where it will be sent the email
     * @param subject - subject of email
     * @param message - message content will be sent
     * @throws NoSuchProviderException if provider for the given provider is not
     * found.
     * @throws MessagingException whether other errors were found.
     *
     */
    public void sendMail(String from, String to, String subject, String message, String type) throws NoSuchProviderException, MessagingException {

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp"); // define protocol as SMTP
        props.put("mail.smtp.starttls.enable", "true");// enable TLS
        props.put("mail.smtp.host", mailServer); // SMTP server, default - GMAIL
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.user", from);
        props.put("mail.debug", "true");// enable debug mode
        props.put("mail.smtp.port", mailServerPort); // server port
        props.put("mail.smtp.socketFactory.port", mailServerPort); // same port for socket
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //Creates an authenticator will be used below
        SimpleAuth auth = new SimpleAuth(email, password);

        //Session - object will be realizes the connection with server
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(true);

        // Object wich contains the message
        Message msg = new MimeMessage(session);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setFrom(new InternetAddress(from));
        msg.setSubject(subject);
        msg.setContent(message, type);

        // the onject will be really sending the data
        Transport tr = session.getTransport("smtp");
        /*
         * define: 1 = mail server - default smtp GMAIL, 2 = username of server,
         * 3 - password for username
         */
        tr.connect(mailServer, email, password);
        msg.saveChanges(); // don't forget this

        //sending the message
        tr.sendMessage(msg, msg.getAllRecipients());

        tr.close();

    }
}

/**
 * Class used for create the susceptible user of authentication on mail server.
 *
 * @see Authenticator
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
class SimpleAuth extends Authenticator {

    public String username = null;
    public String password = null;

    public SimpleAuth(String user, String pass) {
        username = user;
        password = pass;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}