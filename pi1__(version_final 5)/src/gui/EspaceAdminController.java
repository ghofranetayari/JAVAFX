/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class EspaceAdminController implements Initializable {

    @FXML
    private Button NavToEve1;
    @FXML
    private AnchorPane afficherdemande;
    @FXML
    private Button gererboutique;
    @FXML
    private Button gererevenements;
    @FXML
    private Button gereravis;
    @FXML
    private Button gereruser;
    @FXML
    private Button gerercatrefidelite;
    @FXML
    private Button espaceclienthome;
    @FXML
    private Button Afficherreclamation;
    @FXML
    private Button Afficherdemande;
    @FXML
    private Button Statistique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gererboutique(ActionEvent event) {
          try {

            Parent page1 = FXMLLoader.load(getClass().getResource("ProduitFXML.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }

    }


    @FXML
    private void gererevenements(ActionEvent event) {
         try {

            Parent page1 = FXMLLoader.load(getClass().getResource("TableviewEvents.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }

    }

    @FXML
    private void gereravis(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("Avis.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }


    }

    @FXML
    private void gereruser(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("TableView.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }


    }

    @FXML
    private void gerercartefidelite(ActionEvent event) {
        
          try {

            Parent page1 = FXMLLoader.load(getClass().getResource("CarteFideliteAdmin.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }

        
        
    }

    @FXML
    private void espaceclienthome(ActionEvent event) {
         try {

            Parent page1 = FXMLLoader.load(getClass().getResource("RechercheWindow.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void Afficherreclamation(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("adminrec.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void Afficherdemande(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("adminDemande.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void Statistique(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }


    }
    
    @FXML
    void NavToEve(ActionEvent event) {
        
   try {

            Parent page1 = FXMLLoader.load(getClass().getResource("GUIEVENMENTAdmin.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }

    }
}
