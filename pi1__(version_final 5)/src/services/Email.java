/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author ghofr
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 
import entities.OffreSpecialEvenment; 

public class Email {
    
    public static void  Email(OffreSpecialEvenment e) {
        final String username = "achraf.boubaker@esprit.tn";
        final String password = "n7ebnmout";

        /*Properties props  = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");*/
       Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, 
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
        });           

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("achraf.boubaker@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("achraf.boubaker@esprit.tn"));
            message.setSubject("First");
            String text = "Dear Achraf,\n\n"
                    + "We hope this message finds you well. As a valued member of our community, we are excited to invite you to an exclusive event that we believe will greatly interest you.\n\n"
                    + "Event Details:\n"
                    + "Name: \n" + e.getTitre()
                    + "Date: \n" +e.getDate_depart()
                    + "Location: \n" + e.getDestination()
                    + "Why This Event is Special for You:\n"
                    + e.getDescription()
                    + "[List key activities or presentations during the event.]\n\n"
                    + "How to RSVP:\n"
                    + "To reserve your spot at this special event, please reply to this email or visit .\n\n"
                    + "Additional Information:\n"
                    + "[Provide any additional details or special instructions relevant to the event.]\n\n"
                    + "We believe this event is an excellent opportunity for you to [benefits of attending the event] and connect with like-minded individuals who share your interests.\n\n"
                    + "Should you have any questions or require further information, please do not hesitate to reach out to our event coordinator at [Contact Email or Phone Number].\n\n"
                    + "We look forward to welcoming you to this exclusive event and making it an unforgettable experience for you.\n\n"
                    + "Sincerely,";
            Transport.send(message);

            System.out.println("Email has been sent succesfully...");   
        }
        catch(MessagingException ex) {
            throw new RuntimeException(ex);
        }


    }
}
