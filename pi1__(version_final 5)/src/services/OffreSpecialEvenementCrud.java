/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.OffreSpecialEvenment.NiveauCarte;
import com.mysql.jdbc.MySQLConnection;
import entities.OffreSpecialEvenment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import entities.CarteFidelite;
import Utils.MyConnection; 

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.CarteFideliteCrud; 
import services.Email;

/**
 *
 * @author ghofr
 */
public class OffreSpecialEvenementCrud {

    Connection cnx2;
    public OffreSpecialEvenementCrud() { 
        cnx2= MyConnection.getInstance().getCnx();
        
    }
    
    
    public void ajouterOffreSpecialEvenemnt(){
        try {
            String requete = "INSERT INTO OffreSpecialEvenment (titre,description, date_depart,prix, catégorie, guide_id, destination, image , niveau)"+
                    " VALUES ('Cultural Tour','description...','2023-10-11',1500,'Culture',123,'France','musée.jpg', 'bronze')";
            
            
            
           Statement st =   cnx2.createStatement();
           st.executeUpdate(requete); //execute update QUE pour les req de maj (insert,update,delete),on utilise executeQuery (select)
            System.out.println("Offre ajouté avec succés"); 
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
    }
 

    public void ajouterOffreSpecialEvenment (OffreSpecialEvenment e){
        try {
String requete2 = "INSERT INTO OffreSpecialEvenment (titre, description, prix, date_depart, catégorie, guide_id, destination, image, niveau)" +
                 " VALUES (?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getDescription());
            pst.setFloat(3,e.getPrix());
            pst.setDate(4, e.getDate_depart());
            pst.setString(5, e.getCatégorie());
            pst.setInt(6, e.getGuide_id());
            pst.setString(7, e.getDestination());
            pst.setString(8, e.getImage()); 
            pst.setString(9,e.getNiveau().toString());            
            pst.executeUpdate();
            System.out.println("Offre Special ajoutée ! ");
            services.Email.Email(e);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
        public void modifierOffreSpecialEvenment (OffreSpecialEvenment e, int id ){
        try {
                   String requete2 = "UPDATE offreSpecialEvenment SET titre=?,description=?,prix=?, date_depart=?, catégorie=?, guide_id=?, destination=?, image=?, niveau=? where IdOffreSpecialEvenment=?";
            
       PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getDescription());
            pst.setFloat(3,e.getPrix());
            pst.setDate(4, e.getDate_depart());
            pst.setString(5, e.getCatégorie());
            pst.setInt(6, e.getGuide_id());
            pst.setString(7, e.getDestination());
            pst.setString(8, e.getImage()); 
            pst.setString(9,e.getNiveau().toString());            
        pst.setInt(10, id);

        pst.executeUpdate();
        System.out.println("OffreSpecial modifié !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

        }
   

    
    
    
        public void supprimerOffreSpecialEvenmet (int id){
        try {
            String requete2 = "DELETE FROM OffreSpecialEvenment WHERE IdOffreSpecialEvenment  =?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Offre Special supprimé ! ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
        }

public List<OffreSpecialEvenment> afficherOffreSpecial(int userId) {
    List<OffreSpecialEvenment> myList = new ArrayList<>();
    try {
        String requete3 = "SELECT * " +
                "FROM OffreSpecialEvenment " +
                "INNER JOIN Cartefidelite ON OffreSpecial.niveau = Cartefidelite.niveau " +
                "WHERE Cartefidelite.IdUser = ?";

        PreparedStatement st = cnx2.prepareStatement(requete3);
        st.setInt(1, userId); // Set the user ID parameter
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            OffreSpecialEvenment e = new OffreSpecialEvenment();
            e.setIdOffreSpecialEvenment(rs.getInt("IdOffreSpecialEvenment"));
            e.setTitre(rs.getNString("titre"));
            e.setDescription(rs.getNString("description"));
            e.setDate_depart(rs.getDate("date_depart"));
            e.setPrix(rs.getFloat("prix"));
            e.setCatégorie(rs.getNString("catégorie"));
            e.setGuide_id(rs.getInt("guide_id"));
            e.setDestination(rs.getNString("destination"));
            e.setImage(rs.getNString("image"));
            String niveauString = rs.getString("niveau");
            OffreSpecialEvenment.NiveauCarte niveau = OffreSpecialEvenment.NiveauCarte.valueOf(niveauString);
            e.setNiveau(niveau);
            myList.add(e);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the SQL exception appropriately
    }
    return myList;
}     

    
public List<OffreSpecialEvenment> afficherOffreSpecial() {
    List<OffreSpecialEvenment> myList = new ArrayList<>();
    try {
        String requete3 = "SELECT * FROM OffreSpecialEvenment";

        PreparedStatement st = cnx2.prepareStatement(requete3);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            OffreSpecialEvenment e = new OffreSpecialEvenment();
            e.setIdOffreSpecialEvenment(rs.getInt("IdOffreSpecialEvenment"));
            e.setTitre(rs.getString("titre"));
            e.setDescription(rs.getString("description"));
            e.setDate_depart(rs.getDate("date_depart"));
            e.setPrix(rs.getFloat("prix"));
            e.setCatégorie(rs.getString("catégorie"));
            e.setGuide_id(rs.getInt("guide_id"));
            e.setDestination(rs.getString("destination"));
            e.setImage(rs.getString("image"));
            String niveauString = rs.getString("niveau");
            OffreSpecialEvenment.NiveauCarte niveau = OffreSpecialEvenment.NiveauCarte.valueOf(niveauString);
            e.setNiveau(niveau);
            myList.add(e);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the SQL exception appropriately
    }
    return myList;
} 

public ObservableList<OffreSpecialEvenment> listeDesOffres() {
    ObservableList<OffreSpecialEvenment> myList = FXCollections.observableArrayList();

    try {
        String requete = "SELECT * FROM OffreSpecialEvenment";

        Statement st = MyConnection.getInstance().getCnx().createStatement();

        ResultSet rs = st.executeQuery(requete);

        while (rs.next()) {
            OffreSpecialEvenment offre = new OffreSpecialEvenment();
            offre.setIdOffreSpecialEvenment(rs.getInt("IdOffreSpecialEvenment"));
            offre.setImage(rs.getString("image"));
            offre.setTitre(rs.getString("titre"));
            offre.setDescription(rs.getString("description"));
            offre.setPrix(rs.getFloat("prix"));
            offre.setDate_depart(rs.getDate("date_depart"));
            offre.setCatégorie(rs.getString("catégorie"));
            offre.setGuide_id(rs.getInt("guide_id"));
            offre.setDestination(rs.getString("destination"));
            String niveauStringValue = rs.getString("niveau");
            OffreSpecialEvenment.NiveauCarte niveauValue = OffreSpecialEvenment.NiveauCarte.valueOf(niveauStringValue);
            offre.setNiveau(niveauValue);

            myList.add(offre);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
} 

public static OffreSpecialEvenment getEventById(int eventId) {
    OffreSpecialEvenment event = null; 
    Connection cnx3 = MyConnection.getInstance().getCnx();
    String sql = "SELECT * FROM offrespecialevenment WHERE IdOffreSpecialEvenment = ?";

    try {
         PreparedStatement statement = cnx3.prepareStatement(sql); 
        statement.setInt(1, eventId);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                    event = new OffreSpecialEvenment(
                    resultSet.getString("catégorie"),
                    resultSet.getDate("date_depart"),
                    resultSet.getString("description"),
                    resultSet.getString("destination"),
                    resultSet.getInt("guide_id"),
                    resultSet.getString("image"),
                    resultSet.getFloat("prix"),
                    resultSet.getString("titre"),
                    NiveauCarte.valueOf(resultSet.getString("niveau"))
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return event;
}
}


    

