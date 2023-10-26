/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import entities.Commande;
import entities.Produit;
import entities.User;
import services.CommandeCRUD;
import services.ProduitCRUD;
import services.UserCRUD;
import Utils.InvoiceGeneratorExample;
import java.io.IOException;
import java.util.Optional;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import java.util.List;

public class StoreController implements Initializable {
      private int  x;
    public void takeid30(int ah){
    this.x=ah;
        System.out.println("fmf"+ah);
    }

    @FXML
    private TableView<Produit> Amira_tableView_store;
    @FXML
    private TableColumn<Produit, String> Amira_colNom_store;
    @FXML
    private TableColumn<Produit, Double> Amira_colPrixUnitaire_store;
    @FXML
    private TableColumn<Produit, Integer> Amira_colStock_store;

    @FXML
    private TextField Amira_NomProduit_store;
    @FXML
    private TextField Amira_PrixUnitaireProduit_store;
    @FXML
    private TextField Amira_Stock_store;
    @FXML
    private Button Amira_save_BT_store;
    @FXML
    private ImageView Amira_imageView_store;
    @FXML
    private TableColumn<?, ?> Amira_colImage_store;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurez les colonnes du TableView
        Amira_colNom_store.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Amira_colPrixUnitaire_store.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        Amira_colStock_store.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Chargez les données de la base de données dans le TableView
        ProduitCRUD produitCRUD = new ProduitCRUD();
        ObservableList<Produit> produits = FXCollections.observableArrayList(produitCRUD.listeDesProduits());
        Amira_tableView_store.setItems(produits);

        // Réagissez aux clics de ligne dans le TableView
        Amira_tableView_store.setOnMouseClicked((MouseEvent event) -> {
            Produit selectedProduit = Amira_tableView_store.getSelectionModel().getSelectedItem();
            if (selectedProduit != null) {
                Amira_NomProduit_store.setText(selectedProduit.getNom());
                Amira_PrixUnitaireProduit_store.setText(String.valueOf(selectedProduit.getPrixUnitaire()));
                Amira_Stock_store.setText(String.valueOf(selectedProduit.getStock()));
            }
        });
    }

    @FXML
    private void Amira_save_BT(ActionEvent event) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation d'achat");
        confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir acheter ce produit ?");
        ButtonType ouiButton = new ButtonType("Oui");
        ButtonType nonButton = new ButtonType("Non");
        confirmationAlert.getButtonTypes().setAll(ouiButton, nonButton);

        UserCRUD userCRUD = new UserCRUD();
        // Récupérez l'utilisateur actuellement connecté (par exemple, à partir d'une session ou d'un système d'authentification).
        // Remplacez "1" par l'ID de l'utilisateur connecté.
        
        
        System.out.println("test amira"+x);
        ////////////////////////////
        User utilisateur = userCRUD.getUserById(x);
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.get() == ouiButton && utilisateur != null) {
            Produit produitSelectionne = Amira_tableView_store.getSelectionModel().getSelectedItem();

            if (produitSelectionne != null) {
                ajouterCommande(utilisateur, produitSelectionne);
            }
        }
    }
/*
    private void ajouterCommande(User utilisateur, Produit produit) {
        int use = 15;
        int aaa = produit.getId();
        CommandeCRUD c = new CommandeCRUD();
        int nombreDeCommandes = c.getNombreDeCommandesUtilisateur(utilisateur.getId());

        // Vérifiez si l'utilisateur a passé 2 commandes
        if (nombreDeCommandes >= 2) {
            // L'utilisateur a passé 2 commandes, offrez un produit gratuit
            System.out.println("Félicitations, vous avez un produit gratuit : " + produit.getNom());
            
            // Ajoutez le produit gratuit ici

            // Réinitialisez le compteur de commandes pour l'utilisateur
            c.reinitialiserCompteurCommandes(utilisateur.getId());
        }

        // Ajoutez la commande normalement
        Commande commande = new Commande(utilisateur.getId(), produit.getId(), "C:\\Users\\Amira\\Desktop\\semestre7\\PI\\EpicJourneys\\invoice_" + use + "_" + aaa + ".pdf");
        CommandeCRUD commandeCRUD = new CommandeCRUD();
        commandeCRUD.ajouterEntities(commande);
        System.out.println("Commande ajoutée avec succès !");
        
        // Incrémentez le compteur de commandes pour l'utilisateur
        commandeCRUD.incrementerCompteurCommandes(utilisateur.getId());

        // Affichez la facture
        afficherFacture(commande.getPathFacture());
    }*/



    private void ajouterCommande(User utilisateur, Produit produit) {
        // Créez une instance de la classe Commande avec les détails de la commande.
        int use=x;
        int aaa=produit.getId();
        
         rrr.main2();
        
        Commande commande = new Commande(utilisateur.getId(), produit.getId(), "file:///C:/Users/ghofr/OneDrive/Documents/NetBeansProjects/pi1__(version_final%203)/invoice_"+use+"_"+aaa+".pdf");

        // Ajoutez la commande à la table "commande" en utilisant la classe CommandeCRUD.
        CommandeCRUD commandeCRUD = new CommandeCRUD();
        commandeCRUD.ajouterEntities(commande);

        // Affichez un message de succès ou effectuez d'autres actions nécessaires.
        System.out.println("Commande ajoutée avec succès !");
        //CODE Métier !! si meme user achete 2 produits -> le 3eme gratuit
        
        afficherFacture(commande.getPathFacture());
    }
InvoiceGeneratorExample rrr =new InvoiceGeneratorExample();
    private void afficherFacture(String pathFacture) {
        try {
           rrr.main2();
            // Utilisez un lecteur PDF pour ouvrir le fichier de facture
            Runtime.getRuntime().exec("cmd /c start " + pathFacture);
        } catch (IOException e) {
            // Gérez les erreurs d'ouverture du fichier
            e.printStackTrace();
        }
    }

@FXML
private void rowClicked(MouseEvent event) {
    Produit selectedProduit = Amira_tableView_store.getSelectionModel().getSelectedItem();
    if (selectedProduit != null) {
        // Vous pouvez afficher des informations supplémentaires sur le produit
        Amira_NomProduit_store.setText(selectedProduit.getNom());
        Amira_PrixUnitaireProduit_store.setText(String.valueOf(selectedProduit.getPrixUnitaire()));
        Amira_Stock_store.setText(String.valueOf(selectedProduit.getStock()));

        // Récupérez le chemin de l'image à partir de la base de données
        String imagePath = selectedProduit.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            // Chargez l'image depuis le chemin stocké dans la base de données et affichez-la
            Image image = new Image("file:" + imagePath); // Utilisez "file:" pour indiquer que c'est un fichier local
            Amira_imageView_store.setImage(image);
            
        }
    }
}}


