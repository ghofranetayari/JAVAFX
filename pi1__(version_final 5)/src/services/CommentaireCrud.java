/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author ghofr
 */
import Utils.MyConnection;
import entities.Commentaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saif chaalene
 */
public class CommentaireCrud {
        Connection cnx ;
    public CommentaireCrud(){
        cnx= MyConnection.getInstance().getCnx();
    
    }
    
    public void AjouterCommantaire( String c,int id){
        String rq="INSERT INTO commantaire (contenu,IdEvennement)"
                 + "VALUE (?,?)";
        
        try {
            PreparedStatement ps= cnx.prepareStatement(rq);
            ps.setString(1, c);
             ps.setInt(2, id);
            ps.executeUpdate();
             System.out.println("commentaire ajoutee");
            
        } catch (SQLException ex) {
System.err.println(ex.getMessage());        }
        
    }
    
    public void ModifierCommentaire (int IdCommentaireModifier ,String c,int i){
  
   String rqt = "UPDATE commantaire SET IdCommentaire = ?, contenu = ? , IdEvennement = ? WHERE IdCommentaire =? ";
        try {
            
            PreparedStatement pr = cnx.prepareStatement(rqt);

       pr.setInt(1, IdCommentaireModifier);
            pr.setInt(4, IdCommentaireModifier);
            pr.setString(2, c);
            pr.setInt(3, i);
            pr.executeUpdate();
            System.out.println("lignes modifiées ");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }

    
        
       public void SuprimerCommantaire( int IdSup){
               try {
            String requete2 = "DELETE FROM commantaire WHERE IdCommentaire =?";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, IdSup);
            pst.executeUpdate();
            System.out.println("commentaire supprimé ! ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       }
       
       
       
       
       
        public List<Commentaire> AfficherCommentaire(int c) {
         List<Commentaire> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commantaire WHERE IdEvennement = ?";
           PreparedStatement st = cnx.prepareStatement(requete);
           st.setInt(1, c);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                    Commentaire p = new Commentaire();
                p.setIdCommentaire(rs.getInt(1));
                p.setContenu(rs.getString(2));
               
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
         public List<Commentaire> AfficherCommentaire2(int c) {
         List<Commentaire> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commantaire WHERE IdCommentaire = ?";
           PreparedStatement st = cnx.prepareStatement(requete);
           st.setInt(1, c);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                    Commentaire p = new Commentaire();
                p.setIdCommentaire(rs.getInt(1));
                p.setContenu(rs.getString(2));
               
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
    
