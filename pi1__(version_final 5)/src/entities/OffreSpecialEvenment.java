/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;
import java.util.logging.Logger;
import javafx.scene.layout.BackgroundSize;
import org.omg.CORBA.LongHolder;
import entities.CarteFidelite.NiveauCarte;
/**
 *
 * @author ghofr
 */
public class OffreSpecialEvenment {  
    private Date date_depart; 
    private String description,image,titre,destination,catégorie; 
    private float prix; 
    private int guide_id; 
    
    
    
    private int IdOffreSpecialEvenment; 

    public OffreSpecialEvenment( String catégorie, Date date_depart, String description, String destination, int guide_id, String image, float prix, String titre,NiveauCarte niveau) {
                this.catégorie = catégorie; 
                this.date_depart=date_depart ;
                this.description=description;
                this.destination=destination; 
                this.guide_id = guide_id;
                this.image = image;
                this.prix = prix;
                this.titre =titre;
                this.niveau = niveau;
    }
    
    public NiveauCarte niveau;
     

 public String getTitre() {
        return titre;
    }  

    
    public Date getDate_depart() {
        return date_depart;
    }
    
  
    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    
    public String getDescription() {
        return description;
    }

   
    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getImage() {
        return image;
    } 

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
 
    
    public int getIdOffreSpecialEvenment() {
        return IdOffreSpecialEvenment;
    }
    

    
    public void setImage(String image) {
        this.image = image;
    }

    
    public String getDestination() {
        return destination;
    }

   
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCatégorie() {
        return catégorie;
    }

    public void setCatégorie(String catégorie) {
        this.catégorie = catégorie;
    }

    public float getPrix() {
        return prix;
    }

    
    public void setPrix(float prix) {
        this.prix = prix;
    }

    
    public int getGuide_id() {
        return guide_id;
    }

  
    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
    }
 



    public OffreSpecialEvenment(NiveauCarte niveau) {
        super(); 
        this.niveau = niveau;
    }


  

   public OffreSpecialEvenment() { 
        
    }


    public NiveauCarte getNiveau() {
        return niveau;
    }

   public void setNiveau(NiveauCarte niveau) {
        this.niveau = niveau;
    }  
  public  void setIdOffreSpecialEvenment(int IdOffreSpecialEvenment) 
    { 
                this.IdOffreSpecialEvenment = IdOffreSpecialEvenment;

    }

    
 @Override  
public String toString() { 
    return super.toString() + "Niveau Carte :" + this.niveau; 
    
    
}  
public enum NiveauCarte { 
    bronze , 
    silver , 
    gold, 
}

}

    