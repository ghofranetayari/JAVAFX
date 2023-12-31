/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import Utils.MyConnection;
import services.InterfaceCRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ghofr
 */
public class CommandeCRUD implements InterfaceCRUD<Commande> {

    private Connection connection;

    // Constructeur sans arguments
    public CommandeCRUD() {
       
    }

    @Override
public void ajouterEntities(Commande commande) {
    try { connection = MyConnection.getInstance().getCnx();
        String requete = "INSERT INTO Commande (userId, productId, pathFacture) VALUES (?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, commande.getUserId());
        pst.setInt(2, commande.getProductId());
        pst.setString(3, commande.getPathFacture());
        pst.executeUpdate();

        ResultSet generatedKeys = pst.getGeneratedKeys();
        if (generatedKeys.next()) {
            commande.setId(generatedKeys.getInt(1));
        }

        System.out.println("Commande ajoutée avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

@Override
public List<Commande> listeDesEntities() {
    List<Commande> commandes = new ArrayList<>();
    try {
        String requete = "SELECT id, userId, productId, pathFacture FROM Commande";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Commande commande = new Commande();
            commande.setId(rs.getInt("id"));
            commande.setUserId(rs.getInt("userId"));
            commande.setProductId(rs.getInt("productId"));
            commande.setPathFacture(rs.getString("pathFacture"));
            commandes.add(commande);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return commandes;
}

@Override
public void modifierEntities(Commande commande) {
    try {
        String requete = "UPDATE Commande SET userId = ?, productId = ?, pathFacture = ? WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(requete);
        pst.setInt(1, commande.getUserId());
        pst.setInt(2, commande.getProductId());
        pst.setString(3, commande.getPathFacture());
        pst.setInt(4, commande.getId());
        pst.executeUpdate();
        System.out.println("Commande modifiée avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

@Override
public void supprimerEntities(Commande commande) {
    try {
        String requete = "DELETE FROM Commande WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(requete);
        pst.setInt(1, commande.getId());
        pst.executeUpdate();
        System.out.println("Commande supprimée avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

}
//////////////////////////////////////////////////
   /* public int getNombreDeCommandesUtilisateur(int userId) {
        int nombreDeCommandes = 0;
        String query = "SELECT COUNT(*) FROM commandes WHERE userId = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nombreDeCommandes = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreDeCommandes;
    }

     public void reinitialiserCompteurCommandes(int userId) {
        String query = "UPDATE user SET commandes_count = 0 WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void incrementerCompteurCommandes(int userId) {
        String query = "UPDATE utilisateurs SET commandes_count = commandes_count + 1 WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
} 
