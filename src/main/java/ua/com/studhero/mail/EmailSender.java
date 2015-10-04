package ua.com.studhero.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.com.studhero.model.entity.User;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

public class EmailSender {

    private final static String USER_NAME = "slavko@gmail.com";
    private final static String PASSWORD = "pswd";
    private final static String INTERNET_PROVIDER = "Thanks for joining us! \"StudHero\"";
    private final static String ENGINEERS = "Engineers";
    public final static String BAN_ACCOUNT = "Your account is temporarily unavailable due to security reasons";
    public final static String SUBJECT_REGISTRATION = "Registration in StudHero ";
    public final static String SUBJECT_BANNED = "Your account has been blocked";
    public final static String SUBJECT_NEW_TASK = "New Task";
    public final static String CHANGE_PASSWORD = "Password has been successfully changed";
    public final static String CUSTOMER_REGISTRATION_TEMPLATE = "/regTemplate.ftl";
    public final static String GENERIC_TEMPLATE = "/mailTemplate.ftl";
    private static Session session;

    static {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true);//If true, attempt to authenticate the user using the AUTH command. Defaults to false.
        props.put("mail.smtp.starttls.enable", "true"); //If true, enables the use of the STARTTLS command  to switch the connection to a TLS-protected connection before issuing any login commands.
        props.put("mail.smtp.host", EmailProperties.GMAIL.getHost());     //The SMTP server to connect to.
        props.put("mail.smtp.port", EmailProperties.GMAIL.getPort());//The SMTP server port to connect to
        props.put("mail.smtp.ssl.trust", EmailProperties.GMAIL.getHost());
        //If set, and a socket factory hasn't been specified, enables use of a MailSSLSocketFactory. If set to "*", all hosts are trusted.
        // If set to a whitespace separated list of hosts, those hosts are trusted.
        // Otherwise, trust depends on the certificate the server presents.

        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });

    }


    /**
     * send email to  group   of users
     *
     * @param users    - group of users
     * @param task     - task name
     * @param fullPath - path to templates
     */
    public static void sendEmailToGroup(List<User> users, String task, String fullPath) {

        ArrayList<String> emails = new ArrayList<String>(users.size());
        for (User user : users) {
            emails.add(user.getEmail());
        }

        StringBuilder body = new StringBuilder();
        body.append("<p>New task has been created!</p> <p style=\"text-transform:none;\">TaskName: <b>");
        body.append(task);
        body.append("</b></p>");

        send(emails, ENGINEERS, SUBJECT_NEW_TASK, body.toString(), getTemplate(GENERIC_TEMPLATE, fullPath));
    }

    /**
     * send email which will notify changing user's password
     *
     * @param user        - recipient
     * @param newPassword -new password
     * @param fullPath    -   path to templates
     */
    public static void sendChangedPasswordToUser(final User user, String newPassword, String fullPath) {
        StringBuilder to = new StringBuilder();
        to.append("Hello, "+ user.getEmail());
        //to.append(user.getFirstName()).append(" ").append(user.getLastName());
        StringBuilder body = new StringBuilder();

        body.append("<p>Your password has been successfully changed!</p> <p style=\"text-transform:none;\">New password is: ");
        body.append("<a style=\"padding: 10px;font-size: 10px;text-decoration: none;background: #1C80C9;color: #E5F5FF;border-radius: 6px;-moz-border-radius: 6px;-webkit-border-radius: 6px\">");
        body.append(newPassword);
        body.append("</a></p></p>");

        send(new ArrayList<String>() {{
            add(user.getEmail());
        }}, to.toString(), CHANGE_PASSWORD, body.toString(), getTemplate(GENERIC_TEMPLATE, fullPath));
    }

    /**
     * send email which will notify  engineer's password
     *
     * @param user     - recipient
     * @param password - in this case password of user
     * @param fullPath -path to templates
     */
    public static void sendRegistrationEmailToEngineer(final User user, String password, String fullPath) {
        formEmailRecipient(user, password, getTemplate(GENERIC_TEMPLATE, fullPath));
    }

    public static void notifyUserAboutBlockedAccount(final User user, String fullPath) {
        StringBuilder to = new StringBuilder();
        to.append(user.getEmail());
//        to.append(user.getFirstName()).append(" ").append(user.getLastName());
        send(new ArrayList<String>() {{
            add(user.getEmail());
        }}, to.toString(), SUBJECT_BANNED, BAN_ACCOUNT, getTemplate(GENERIC_TEMPLATE, fullPath));
    }


    /**
     * send email which will notify  user's password
     *
     * @param user     - recipient
     * @param password -  in this case password of user
     * @param fullPath -path to templates
     */
    public static void sendRegistrationEmailToCustomer(final User user, String password, String fullPath) {
        formEmailRecipient(user, password, getTemplate(CUSTOMER_REGISTRATION_TEMPLATE, fullPath));
    }


    /**
     * @param user     -recipient
     * @param body     - body of email
     * @param template - appropriate template
     */
    private static void formEmailRecipient(final User user, String body, Template template) {
        StringBuilder to = new StringBuilder();
        to.append("Hello, " + user.getEmail());
//        to.append(user.getFirstName()).append(" ").append(user.getLastName());
        send(new ArrayList<String>() {{
            add(user.getEmail());
        }}, to.toString(), SUBJECT_REGISTRATION, body, template);
    }

    /**
     * @param emails
     * @param personName
     * @param subject    of the email
     * @param body       of the email
     * @param template
     */
    private static void send(ArrayList<String> emails, String personName, String subject, String body, Template template) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME));
            //setting recipient
            for (String email : emails) {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
            }
            message.setSubject(subject);
            //define constant to fill template
            final String to = "to";
            final String bodyValue = "body";
            final String from = "from";

            Map<String, String> rootMap = new HashMap<String, String>();
            rootMap.put(to, personName);
            rootMap.put(bodyValue, body);
            rootMap.put(from, INTERNET_PROVIDER);
            Writer out = new StringWriter();
            try {
                template.process(rootMap, out);
            } catch (TemplateException e) {
                //todo logger
                System.out.println("error occured when filling template");
            } catch (IOException e) {
                //todo logger
                e.printStackTrace();
            }

            message.setContent(out.toString(), "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            //todo logger
        }


    }

    /**
     * return  Template
     *
     * @param templateName - template name
     * @param tempateDir   - to get template path you should invoke context.getRealPath("/WEB-INF/mail/") ;
     * @return
     */
    private static Template getTemplate(String templateName, String tempateDir) {
        Configuration cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new java.io.File(
                    tempateDir));
        } catch (IOException e) {
            e.printStackTrace();
            //todo logger
        }
        Template template = null;
        try {
            template = cfg.getTemplate(templateName);

        } catch (IOException e) {
            //todo logger
            System.out.println("error occured when getting template file");
        }

        return template;
    }
    public static void send(){
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "slavko@gmail.com");
        props.put("mail.smtp.password", "pswd");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);



        Session session = Session.getInstance(props,null);
        MimeMessage message = new MimeMessage(session);

        System.out.println("Port: "+session.getProperty("mail.smtp.port"));

        // Create the email addresses involved
        try {
            InternetAddress from = new InternetAddress("slavko.kasp@gmail.com");
            message.setSubject("Yes we can");
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("kaspyar@gmail.com"));

            // Create a multi-part to combine the parts
            Multipart multipart = new MimeMultipart("alternative");

            // Create your text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("some text to send");

            // Add the text part to the multipart
            multipart.addBodyPart(messageBodyPart);

            // Create the html part
            messageBodyPart = new MimeBodyPart();
            String htmlMessage = "Our html text";
            messageBodyPart.setContent(htmlMessage, "text/html");


            // Add html part to multi part
            multipart.addBodyPart(messageBodyPart);

            // Associate multi-part with message
            message.setContent(multipart);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "username", "password");
            System.out.println("Transport: "+transport.toString());
            transport.sendMessage(message, message.getAllRecipients());


        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
