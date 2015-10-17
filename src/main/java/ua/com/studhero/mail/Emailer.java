package ua.com.studhero.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.com.studhero.Key;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author kaspyar.
 */
public class Emailer {
    private static final String FULL_PATH = Key.PATH + Key.PROJECT + Key.MAIL_TEMPLATES_DIR;

    private static String from;
    private static String host;
    private static Properties properties;
    private static Session session;

    static {
        from = "studhero@gmail.com";
        host = "localhost";
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        session = Session.getDefaultInstance(properties);
    }

    private static void send(String reciever, String personName, String subject, String body, Template template) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever));
            message.setSubject(subject);

            final String to = "to";
            final String bodyValue = "body";
            final String from = "from";

            Map<String, String> rootMap = new HashMap<String, String>();
            rootMap.put(to, personName);
            rootMap.put(bodyValue, body);
            rootMap.put(from, Key.STUDHERO);
            Writer out = new StringWriter();
            try {
                template.process(rootMap, out);
            } catch (TemplateException e) {
                //todo logger
            } catch (IOException e) {
                //todo logger
                e.printStackTrace();
            }
            message.setContent(out.toString(), "text/html");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void sendRegistrationEmailToStudent(String to, String name, String password) {
        send(to, name, Key.EMAIL_REGISTRATION_SUBJECT, password, getTemplate(Key.REGISTRATION_TEMPLATE));
    }

    public static void sendRegistrationEmailToCompany(String to, String name, String password) {
        send(to, name, Key.EMAIL_REGISTRATION_SUBJECT, password, getTemplate(Key.REGISTRATION_TEMPLATE_COMPANY));
    }

    private static Template getTemplate(String templateName) {
        Configuration cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new java.io.File(
                    FULL_PATH));
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
}
