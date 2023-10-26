/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffreSpecialEGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class AjouterOffreSpecialController implements Initializable {

    @FXML
    private TextField fxTitreOffre;
    @FXML
    private TextField fxPrixOffre;
    @FXML
    private TextField fxGuideIdOffre;
    @FXML
    private TextField fxDestinationOffre;
    @FXML
    private Button fxAjouterOffre;
    @FXML
    private Button fxRetourInitial;
    @FXML
    private DatePicker fxDateDepartOffre;
    @FXML
    private TextArea fxDescriptionOFfre;
    @FXML
    private TextField fxCattegorieOffre;
    @FXML
    private Button fxImportAchraf;
    @FXML
    private ImageView imageOffre;
    @FXML
    private ComboBox<?> fxNiveauOffre;
    @FXML
    private Label fxAjouterOffrelabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterOffreSpecialE(ActionEvent event) {
    }

    @FXML
    private void retourToSceneInitiale(ActionEvent event) {
    }

    @FXML
    private void fxImportAchraf(ActionEvent event) {
    }
    
}
