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
public class Comments {

/**
 *
 * @author USER
 */
    private int id	;
     private int news_id;
    private String text;
   private Date comment_date;
    private String created_at;

    public Comments(int id, int news_id,String text,Date comment_date) {
        this.id = id;
                this.news_id = news_id;
        this.text = text;
        
      this.comment_date = comment_date;    
    }  

    public Comments(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Comments(int news_id,String text,Date comment_date) {
           this.news_id = news_id;
        
         this.comment_date = comment_date;    

        this.text = text;
    }
    public Comments(int news_id, String text) {
        this.news_id = news_id;
        this.text = text;
    }

    public Comments() {
    }

    public int getId_commentaire() {
        return id;
    }

    public void setId_commentaire(int id) {
        this.id = id;
    }

    public int getId_actualite() {
        return news_id;
    }

    public void setId_actualite(int news_id) {
        this.news_id = news_id;
    }


 

    public String getTexte() {
        return text;
    }

    public void setTexte(String text) {
        this.text = text;
    }

    public Date getDate_commentaire() {
        return comment_date;
    }

    public void setDate_commentaire(Date comment_date) {
        this.comment_date = comment_date;
    }

   
    

   

    public String gettexte() {
        return text;
    }

    public void settexte(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_commentaire=" + id + ", id_actualite=" + news_id + ", texte=" + text + ", date_commentaire=" + comment_date + '}';
    }
   
}
