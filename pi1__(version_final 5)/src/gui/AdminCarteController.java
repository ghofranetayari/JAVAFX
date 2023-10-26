/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Utils.MyConnection;
import com.mysql.jdbc.MySQLConnection;
import entities.CarteFidelite;
import entities.CarteFidelite.EtatCarte;
import entities.CarteFidelite.NiveauCarte;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.CarteFideliteCrud;



public class AdminCarteController implements Initializable { 
        
       
      private CarteFideliteCrud cnx2;
    @FXML
    private Button fxNavToSpecial;
    @FXML
    private Button back;
        public AdminCarteController () 
        { 
        cnx2 = CarteFideliteCrud.getInstance();
        }
    @FXML
    private TableView<CarteFidelite> fxTableCarte;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxIdCarte;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxPtsFidelite;
    @FXML
    private TableColumn<CarteFidelite, Date> fxDateDebut;
    @FXML
    private TableColumn<CarteFidelite, Date> fxDateFin;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxIdClient;
    @FXML
    private TableColumn<CarteFidelite, EtatCarte> fxEtatCarte;
    @FXML
    private TableColumn<CarteFidelite, NiveauCarte> fxNiveauCarte;
    ObservableList<CarteFidelite> listC; 
    int index = -1; 
    Connection conn = null; 
    ResultSet rs = null; 
    PreparedStatement st = null ; 
    @FXML
    private Button fxSuspend; 
    @FXML
    private TextField fxChercher;
    private Label fxLabel1;
    @FXML
    private Button fxActivateCard;
    @FXML
    private Label fxLabel2;
    @FXML
private int getCarte() {
    int idCarte = -1; 
    index = fxTableCarte.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
        CarteFidelite selectedCarte = fxTableCarte.getItems().get(index);
        idCarte = selectedCarte.getIdCarte(); }
    return idCarte;}  
private CarteFideliteCrud carteFideliteCrud;


private void loadCarte() 
{ 
    ObservableList<CarteFidelite> listC= FXCollections.observableArrayList(CarteFideliteCrud.listeDesEntites1());  
    fxTableCarte.setItems(listC);
    
    
}  
@FXML
void NavigateToEventS(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OffreSpecialEGUI/GUIEVENMENTAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

/*    @FXML 
    void NavigateToCarte(ActionEvent event ) 
    { 
        try 
        { 
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CarteFideliteAdmin/GUI/CarteFideliteAdmin.fxml"));
            Parent root = loader.load(); 
            Scene scene = new Scene(root);  
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene) ; 
            stage.show();
        }  
        catch(IOException e) 
        { 
            e.printStackTrace(); 
        }
        
    }
*/ 

        
public void updateTable() 
{ 
     
        fxIdCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("IdCarte"));
    fxPtsFidelite.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("PtsFidelite"));
    fxDateDebut.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Date>("DateDebut"));
    fxDateFin.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Date>("DateFin"));
    fxIdClient.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("id"));
    fxEtatCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite,EtatCarte>("EtatCarte"));
    fxNiveauCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite,NiveauCarte>("NiveauCarte"));
    listC=cnx2.listeDesEntites1(); 
    fxTableCarte.setItems(listC);
    

}

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        updateTable(); 
        loadCarte() ;
        System.err.println(getCarte());
   /* fxSelectAll.selectedProperty().addListener(new ChangeListener<boolean>(){
     @Override 
     public void changed(ObservableValue<? extends boolean> observable, Boolean oldValue,Boolean newvalue) 
     {
         
     }``````````````````````````````
    });*/ 
   
         
    
    fxSuspend.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) { 
                int idCarteToSuspend = getCarte();  
                if (idCarteToSuspend == -1 )
                        { 
                            fxLabel2.setText("plz select a card");
                        }
                else{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to suspend this card?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            
            CarteFideliteCrud cnx2 = CarteFideliteCrud.getInstance(); 
            int x =cnx2.SuspendCarte(idCarteToSuspend);   
            if (x==-1) 
                    { 
                      fxLabel2.setText("carte already suspended"); 
                      fxLabel2.setTextFill(Color.RED);
                      
                    } 
            else if (x==-2 ) 
            { 
            fxLabel2.setText("carte already suspended"); 
            fxLabel2.setTextFill(Color.RED);

 
            }
            else if (x==1) 
            { 
                fxLabel2.setText("successfully suspended"); 
                fxLabel2.setTextFill(Color.GREEN);  
                updateTable();
                

            }
        }
    }
    }
    

}); 

FilteredList<CarteFidelite> filterData = new FilteredList<>(listC, b -> true);
fxChercher.textProperty().addListener((observable, oldValue, newValue) -> {
    filterData.setPredicate((CarteFidelite carte) -> {
        if (newValue == null || newValue.isEmpty()) {
            return true; 
        }

        String lowerCaseFilter = newValue.toLowerCase();
        AtomicBoolean matchFound = new AtomicBoolean(false);

        if (String.valueOf(carte.getIdCarte()).toLowerCase().contains(lowerCaseFilter)) {
            matchFound.set(true);
            System.out.println("Debug: Matching IdCarte - " + carte.getIdCarte());
        }

        if (matchFound.get()) {
            return true; 
           
        } 
       
        else  {
            System.out.println("Debug: Checking other attributes");
            return String.valueOf(carte.getPtsFidelite()).toLowerCase().contains(lowerCaseFilter)
                || String.valueOf(carte.getid()).toLowerCase().contains(lowerCaseFilter)
                || carte.getDateDebut().toString().toLowerCase().contains(lowerCaseFilter)
                || (carte.getDateFin() != null && carte.getDateFin().toString().toLowerCase().contains(lowerCaseFilter))
                || carte.getEtatCarte().toString().toLowerCase().contains(lowerCaseFilter)
                || carte.getNiveauCarte().toString().toLowerCase().contains(lowerCaseFilter);
        }
    });
});
SortedList<CarteFidelite> sortedData = new SortedList<>(filterData) ; 
sortedData.comparatorProperty().bind(fxTableCarte.comparatorProperty()); 
fxTableCarte.setItems(sortedData);


    fxActivateCard.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        
            
            int idCarteToActivate = getCarte();  
            if (idCarteToActivate == -1) 
            { 
                fxLabel2.setText("plz select a card");
            } 
            else{        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Do you want to activate this card?");
                Optional<ButtonType> result = alert.showAndWait();
                 if (result.isPresent() && result.get() == ButtonType.OK) {

            
                {CarteFideliteCrud cnx2 = new CarteFideliteCrud(); 
            int x =cnx2.ActivateCarte(idCarteToActivate);   
            if (x==-1) 
                    { 
                      fxLabel2.setText("carte already activated"); 
                      fxLabel2.setTextFill(Color.RED);
                      
                    } 
            else if (x==-2 ) 
            { 
            fxLabel2.setText("carte already activated"); 
            fxLabel2.setTextFill(Color.RED);

 
            }
            else if (x==1) 
            { 
                fxLabel2.setText("successfully activated"); 
                fxLabel2.setTextFill(Color.GREEN);

            }} 
            } 
        }
    }
}); 



}

    @FXML
    private void back15(ActionEvent event) {
    }


}