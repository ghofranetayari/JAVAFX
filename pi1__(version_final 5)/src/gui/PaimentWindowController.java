/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author ghofr
 */

import Utils.MyConnection;
import com.stripe.exception.StripeException;
import entities.EVENNEMENT;
//import healthconnectjava.services.Emailsender_2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import services.CarteFideliteCrud;
import services.ServicePaymentStripe;

public class PaimentWindowController implements Initializable {


 Connection cnx2;
    @FXML
    private CheckBox chekpt;
    @FXML
    private Label fxDiscount;

    public PaimentWindowController() {
        
        cnx2 = MyConnection.getInstance().getCnx();

    }
    
    ServicePaymentStripe servicePayment;

    
    @FXML
    private Label XprixX ;
   
            
       @FXML
    private TextField numCardField;
    private boolean payed = false;
    @FXML
    private TextField expMoisField;
    @FXML
    private TextField expanneeField;
    @FXML
    private TextField cvvField;
    @FXML
    private AnchorPane pane;
    
    
   
      private int IdE2;

    public void setSharedVariable2(int IdE) {
        this.IdE2 = IdE;
        // System.out.println(IdE);
    }
    
    
    
     
    
    
     private List<EVENNEMENT> evennements = new ArrayList<>();

    public List<EVENNEMENT> getData2() {

        List<EVENNEMENT> evennements = new ArrayList<>();
        EVENNEMENT evennement;
System.out.println("hne ykoli aaaaa " + IdE2);
        // System.out.println(IdE);
        try {

            String requete = "SELECT * FROM evenement WHERE  IdEvennement = ? ";
            PreparedStatement st = cnx2.prepareStatement(requete);

            st.setInt(1, IdE2);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                EVENNEMENT evennemen = new EVENNEMENT();
                evennemen.setDescription(rs.getNString(3));
                evennemen.setGuide_id(rs.getInt(7));
                evennemen.setTitre(rs.getString(2));
                evennemen.setPrix(rs.getInt(5));
                evennemen.setImage(rs.getString(9));
                evennemen.setVille(rs.getString(8));
                evennemen.setDuree(rs.getString(10));
                evennemen.setIdEvenement(rs.getInt(1));
                evennements.add(evennemen);

                System.out.println(evennements);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return evennements;
    }


     private List<EVENNEMENT> evennement = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
             System.out.println("oo no no"+IdE2);
        // TODO
        
        
             evennement.addAll(getData2());

if (evennement.isEmpty()) {
    System.out.println("The 'evennement' list is empty.");
} else {
    System.out.println(evennement.get(0).getPrix());

 XprixX.setText(String.valueOf(evennement.get(0).getPrix())); 


}
        addTextLimiter(expMoisField, 2);
        addTextLimiter(expanneeField, 4);
        addTextLimiter(cvvField, 3);

    } 

            
            
            
    

    @FXML
    private void Payer(ActionEvent event) {
        
      /*    
             evennement.addAll(getData2());

if (evennement.isEmpty()) {
    System.out.println("The 'evennement' list is empty.");
} else {
    System.out.println(evennement.get(0).getPrix());

 int a =(int)evennement.get(0).getPrix(); 


}
        */
        String nom = "chaalene";
        String email = "chaalene.saif@esprit.tn";
        String numCard = numCardField.getText();
        String expMois = expMoisField.getText();
        String exAnnee = expanneeField.getText();
        String cvv = cvvField.getText();

        servicePayment = new ServicePaymentStripe(email, nom, 250, numCard, expMois, exAnnee, cvv);
        // Récupérer la valeur de l'e-mail du champ de saisie


        try {
            servicePayment.payer();

            FXMLLoader fxmlLoader = new FXMLLoader();

            payed = true;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("valid payment");
            alert.show();

        } catch (StripeException ex) {
            Logger.getLogger(PaimentWindowController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("error payment");
            alert.show();

        } 
        CarteFideliteCrud cnx2 = new CarteFideliteCrud(); 
        cnx2.ajouterPts(IdE2, 40);

    }
    @FXML
    public void back(){
       
    }

    public boolean getReturn() {
        return payed; //return what you want controlled by your controller class
    }

   
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    @FXML
    private void chekpt(ActionEvent event) {
         System.out.println("gnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        CarteFideliteCrud cnx2 = new CarteFideliteCrud();  
        float x=cnx2.UtiliserPts(43,evennement.get(0).getPrix()); 
        System.out.println("achref"+x);
        System.out.println("gggggggggggggggggggggg");
        
        
       fxDiscount.setText("le prix est : "+x);
        
      /*
      float y=(evennement.get(0).getPrix())-10;
      
      fxDiscount.setAccessibleHelp(String.valueOf(y));
        */
    }

}
