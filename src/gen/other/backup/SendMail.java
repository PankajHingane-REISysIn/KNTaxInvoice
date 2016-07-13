package gen.other.backup;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author ABC
 */
public class SendMail {
    /*
     * this function send a mail to receiver, parameters are email_id is sender and receiver id, pass is password
     * of email_id and filename1 is backup filename which we want to send
     */

    public SendMail(String email_id, String pass, String filename1) throws MessagingException {

        String host = "smtp.gmail.com";       //host name
        String Password = pass;               //email_id password
        String from = email_id;              //email_id of sender
        String toAddress = email_id;        //email_id of receiver
        String dir = System.getProperty("user.dir");  //get current directory of user
        String filename = dir + "/" + filename1 + ".xml";   //filename of backup file
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        javax.mail.Session session = javax.mail.Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new javax.mail.internet.InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO, toAddress);   //set receiver address

        message.setSubject("AdSuMuDi Database Backup");    //set mail subject

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText("Here is the Database Backup file of AdSuMuDi");  //message

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);       //used for files

        messageBodyPart.setDataHandler(new DataHandler(source));

        messageBodyPart.setFileName(filename);   //find file path

        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());  //send an email
            //System.out.println("Mail Sent Successfully");
            tr.close();

        } catch (SendFailedException sfe) {

            System.out.println(sfe);     //if fail to send mail
        }
    }
}
