/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Email2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Chargez la racine depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EnvoyerEmail.fxml"));
        Parent root = loader.load();

        // Créez une instance de ProduitFXMLController
        EnvoyerEmailController produitController = loader.getController();


        Scene scene = new Scene(root);

        // Configurez la scène et affichez-la sur le stage
        stage.setTitle("Votre Titre");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}