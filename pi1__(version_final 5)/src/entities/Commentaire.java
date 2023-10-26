/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ghofr
 */
public class Commentaire {
    private int IdCommentaire ;
    private String contenu ; 
    private int IdEvennement ;

    public int getIdEvennement() {
        return IdEvennement;
    }

    public void setIdEvennement(int IdEvennement) {
        this.IdEvennement = IdEvennement;
    }

    public Commentaire() {
    }

    public Commentaire(String contenu) {
        this.contenu = contenu;
    }

    public int getIdCommentaire() {
        return IdCommentaire;
    }

    public void setIdCommentaire(int IdCommentaire) {
        this.IdCommentaire = IdCommentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
}