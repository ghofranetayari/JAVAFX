/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.se.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Amira
 */


import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnvoyerEmailController implements Initializable {

    
    @FXML
    private TextField produitdemandétextfieled;
    @FXML
    private TextField adressetextfieled;
    @FXML
    private Button Amira_enregistrer_btn;
    
    
    private void sendConfirmationEmail() {
        final String username = "amira.ayari@esprit.tn";
        final String password = "213JFT0300";

       /* Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");*/
       Properties props = new Properties();
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Spécifiez la version du protocole TLS
//props.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256"); // Spécifiez la suite de chiffrement appropriée




        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("amira.ayari@esprit.tn", "213JFT0300");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amira.ayari@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adressetextfieled.getText()));
            message.setSubject("Stock manquant");

            String p = produitdemandétextfieled.getText();
        

            String emailContent = "<h3>Bonjour Mr/Mme , nous avons recu votre demande de </h3>"
                    + "<p>produit: " + p + "</p>"
               
                    + "<p>Vous recevrez un e-mail dès qu il sera disponible</p>";

            message.setContent(emailContent, "text/html");
            Transport.send(message);

            System.out.println("Email recue...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    private void afficherConfirmation() {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Merci pour votre demande. Confirmez-vous ?");

      
         Optional<ButtonType> result =confirmationAlert.showAndWait();
         if (result.isPresent() && result.get() == ButtonType.OK) {
         sendConfirmationEmail();
         afficherConfirmationmail(); 

         } else {
        Alert verif = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Merci de remplir correctement ces champs correctement!");
        confirmationAlert.showAndWait();
        }
    }
    private void afficherConfirmationmail() {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Nous avons vous envoyé un mail !");
        confirmationAlert.showAndWait();
        
       
    }

    @FXML
    private void Amira_enregistrer_btn(ActionEvent event) {
        afficherConfirmation();
      
        
    }
}