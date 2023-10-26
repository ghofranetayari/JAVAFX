/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class AjoutrecController implements Initializable {

    @FXML
    private ComboBox<?> cbCibleReclamation;
    @FXML
    private DatePicker dtDateReclamation;
    @FXML
    private Button btnAjouterReclamation;
    @FXML
    private TextField tfDescriptionReclamation;
    @FXML
    private Button btnAfficherReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveReclamation(ActionEvent event) {
    }

    @FXML
    private void btnAfficherReclamation(ActionEvent event) {
    }
    
}
