/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;
/**
 *
 * @author ghofr
 */
public class demande {
     private int idDemande;
    private int Id;
    private String destination;
    private Date dateDepart;
    private String type;

    public demande(int idDemande, int Id, String destination, String type) {
        this.idDemande = idDemande;
        this.destination = destination;
        this.Id = Id;
        this.type = type;
    }
    
    public demande () {};
    
    public demande ( int Id, String destination, Date dateDepart, String type ) {

        this.destination = destination;
        this.dateDepart = dateDepart;
        this.type = type;
       
    }
    
     public demande ( String destination, Date dateDepart, String type, int Id ) {
        this.destination = destination;
        this.Id = Id;
        this.dateDepart = dateDepart;
        this.type = type;
       
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "demande{" + "idDemande=" + idDemande + ", Id=" + Id + ", destination=" + destination + ", dateDepart=" + dateDepart + ", type=" + type + '}';
    }

  
    
    
    

    
}
