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
/*
import Utils.MyConnection;
import entities.Recherche;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saif chaalene
 *//*
public class SharedModel {
    private String textData;

   public SharedModel() {
    }
   
    
    
    
    
    
    
    
    
         public List<Recherche> o(){
         List<Recherche> myListr = new ArrayList<>();
          Date specificDate = new Date(31/ 10/ 2023);
         
        try {
            String requete = "SELECT * FROM rechercheevennement";
            Statement stt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rsss = stt.executeQuery(requete);
            while(rsss.next()){
                Recherche p = new Recherche();
                  p.setDateDepartRecherche(specificDate);
               p.setBudgetRechercher(rsss.getInt(3));
                p.setTypeRechercher(rsss.getString(2));
                p.setDestinationRechercher(rsss.getString(1));
               
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
}*/