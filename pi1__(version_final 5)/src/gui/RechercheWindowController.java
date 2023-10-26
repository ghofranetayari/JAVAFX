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

import entities.Recherche;
import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.RechercheEvennementCrud;

//import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author saif chaalene
 */
public class RechercheWindowController implements Initializable {
    
    @FXML
    private Button CarteFidelite1;
    private int  ah;
    
    public void takeid1(int ah){
    this.ah=ah;
        System.out.println("ff"+ah);
    } 
    
    

    @FXML
    private Button Home;
    @FXML
    private Button OurBoutique;
    @FXML
    private Button Ajouter_Reclamtion;
    @FXML
    private Button VotreAvis;
    @FXML
    private Button Demande_Evenement;

    public RechercheWindowController() {
    } 

    @FXML
    private Button rchercher;
    @FXML
    private TextField rdestination;
    @FXML
    private ComboBox<String> rtype;
    @FXML
    private DatePicker rdate;
    @FXML
    private TextField rbudget;
    @FXML
    private ImageView zz;

    @FXML
    private Label rnotf;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] iteams = {"sport", "culture", "nature"};
        rtype.getItems().addAll(iteams);
//String[] posw={"ghaza","palastine","hamas"};
//TextFields.bindAutoCompletion(rdestination,posw);

      //Image myimage = new Image(getClass().getResourceAsStream("maldive.jpg"));
       // zz.setImage(value);

// TODO
    } 
    
    
    

    @FXML

    private void saverecherche(ActionEvent event) throws IOException {
        String rrdestination = rdestination.getText();
        //  sharedModel.setTextData(rrdestination);
        try {

            //ReservationWindowController targetController = loader.getController();
            LocalDate rrdate = rdate.getValue();
            Date rrrdate = java.sql.Date.valueOf(rrdate);

            float rrbudget = Float.parseFloat(rbudget.getText());
            String rrtype = rtype.getValue();
            Recherche r = new Recherche(rrdestination, rrtype, rrbudget, (java.sql.Date) rrrdate);
            if (rrtype != null) {
                RechercheEvennementCrud rc = new RechercheEvennementCrud();
                rc.AjouterRechercheEvennement(r);
                FXMLLoader sourceLoader = new FXMLLoader(getClass().getResource("ReservationWindow.fxml"));
                Parent rooot = sourceLoader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                scene = new Scene(rooot);
                stage.setScene(scene);
                stage.show();

            } else {

                rnotf.setText("you have to choose your type");

            }

        } catch (IOException ex) {
            Logger.getLogger(RechercheWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*public TextField getRdestination() {
        return rdestination;
    }

    public void setRdestination(TextField rdestination) {
        this.rdestination = rdestination;
    }

    public ComboBox<String> getRtype() {
        return rtype;
    }

    public void setRtype(ComboBox<String> rtype) {
        this.rtype = rtype;
    }*/

    @FXML
    private void Votreavis(ActionEvent event) {
        
         try {

            Parent page1 = FXMLLoader.load(getClass().getResource("AjoutAvis.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
        
        System.out.println("hne"+ah);
        try {
 FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutrec.fxml"));
            Parent rooot = loader.load();
            AjoutrecController detailController = loader.getController();
            
            detailController.takeid(ah);
            //detailController.initialize(null, null);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void DemandeEvenement(ActionEvent event) {
         try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutDemande.fxml"));
            Parent rooot = loader.load();
            FXMLController detailController = loader.getController();
            
            detailController.takeid5(ah);
            //detailController.initialize(null, null);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }
    
      @FXML
    void goboutique(ActionEvent event) {
        
         try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Store.fxml"));
            Parent rooot = loader.load();
            StoreController detailController = loader.getController();
            
            detailController.takeid30(ah);
            //detailController.initialize(null, null);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
        

    }
        @FXML
    void NavToCarteFidelite(ActionEvent event) { 
        
    try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ss.fxml"));
            Parent rooot = loader.load();
            SsController detailController = loader.getController();
            
            detailController.takeid300(ah);
            //detailController.initialize(null, null);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
        
    }

    @FXML
    private void Profil(ActionEvent event) {
        
        try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
Parent rooot = loader.load();
ProfilController profilController = loader.getController();
profilController.takeid10(ah);
            //detailController.initialize(null, null);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        } 
        
    }


}
