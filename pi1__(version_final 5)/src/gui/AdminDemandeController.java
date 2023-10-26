/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.demande;
import services.demandeCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ghof
 */
public class AdminDemandeController implements Initializable {

    @FXML
    private TableView<demande> tvDemande;
    @FXML
    private Button btnAccpDemande;
    @FXML
    private TableColumn<demande, Integer> colIdDemande;
    @FXML
    private TableColumn<demande, Integer> colIdClient;
    @FXML
    private TableColumn<demande, String> colDestDemande;
    @FXML
    private TableColumn<demande, Date> colDateDemande;
    @FXML
    private TableColumn<demande, String> colTypeDemande;

    /**
     * Initializes the controller class.
     */
    
        private demande selectedDemande;

    public void setSelectedDemande(demande selectedDemande) {
        this.selectedDemande = selectedDemande;
    }
        public void showdemande() {
        demandeCRUD dcd = new demandeCRUD();
        List<demande> myList = dcd.afficherDemande();

        colIdDemande.setCellValueFactory(new PropertyValueFactory<>("idDemande"));
        colIdClient.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colDestDemande.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDateDemande.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        colTypeDemande.setCellValueFactory(new PropertyValueFactory<>("type"));

        ObservableList<demande> observableList = FXCollections.observableArrayList(myList);
        tvDemande.setItems(observableList);

       
        tvDemande.setRowFactory(tv -> {
            TableRow<demande> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedDemande = row.getItem();
                }
            });
            return row;
        });
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
              showdemande();

        btnAccpDemande.setDisable(true);

        
        tvDemande.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<demande>() {
            @Override
            public void changed(ObservableValue<? extends demande> observable, demande oldValue, demande newValue) {
                if (newValue != null) {
                   
                    btnAccpDemande.setDisable(false);
                } else {
                   
                    btnAccpDemande.setDisable(true);
                }
            }
        });
        // TODO
    }    

    @FXML
    private void approveDemande(ActionEvent event) {
    }
    
}
