/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author ghofr
 */

import Utils.MyConnection;
import entities.Recherche;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saif chaalene
 */
public class SharedModel {
       Connection cnx ;
    public SharedModel(){
        cnx= MyConnection.getInstance().getCnx();
    
    }
    private String textData;

    
    
    
    
    
    
    
    
    
         public List<Recherche> o(){
         List<Recherche> myListr = new ArrayList<>();
     
         
        try {
            String requete = "SELECT * FROM rechercheevennement";
            Statement stt = cnx.createStatement();
            ResultSet rsss = stt.executeQuery(requete);
            while(rsss.next()){
                System.out.println("here we are okay?");
                Recherche p = new Recherche();
                  p.setDateDepartRecherche(rsss.getDate(5));
               p.setBudgetRechercher(rsss.getInt(4));
                System.out.println("and here.?");
                p.setTypeRechercher(rsss.getString(3));
                p.setDestinationRechercher(rsss.getString(2));
                p.setIdRecherche(rsss.getInt(1));
                System.out.println("pls help me"+rsss.getInt(1));
                myListr.add(p);
            }
        } 
           catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    return myListr;
       }
    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }
}