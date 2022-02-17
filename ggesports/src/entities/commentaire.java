/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class commentaire {

/**
 *
 * @author USER
 */
    private int id_commentaire	;
     private int id_actualite;
      private int id_utilisateur;
    private String texte;
    private Date date_commentaire;

    public commentaire(int id_commentaire, int id_actualite,int id_utilisateur,String texte,Date date_commentaire) {
        this.id_commentaire = id_commentaire;
                this.id_actualite = id_actualite;
        this.id_utilisateur = id_utilisateur;

        this.texte = texte;
        
      this.date_commentaire = date_commentaire;    
    }  

    public commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public commentaire(int id_actualite,int id_utilisateur,String texte,Date date_commentaire) {
           this.id_actualite = id_actualite;
        this.id_utilisateur = id_utilisateur;
        
         this.date_commentaire = date_commentaire;    

        this.texte = texte;
    }

    public commentaire() {
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getId_actualite() {
        return id_actualite;
    }

    public void setId_actualite(int id_actualite) {
        this.id_actualite = id_actualite;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    

   

    public String gettexte() {
        return texte;
    }

    public void settexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_commentaire=" + id_commentaire + ", id_actualite=" + id_actualite + ", id_utilisateur=" + id_utilisateur + ", texte=" + texte + ", date_commentaire=" + date_commentaire + '}';
    }

   

   
}
