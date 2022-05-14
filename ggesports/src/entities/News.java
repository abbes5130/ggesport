/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.sql.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author USER
 */
public class News {

      private int id;
    private String title;
    private String bg_img;
    private String img;
    private String description;
    private Date creation_date;

    public News(int id, String title, String bg_img, String img, String description, Date creation_date) {
        this.id = id;
        this.title = title;
        this.bg_img = bg_img;
        this.img = img;
        this.description = description;
        this.creation_date = creation_date;
    }

    public News(int id) {
        this.id = id;
    }

    public News(String title) {
        this.title = title;
    }

    public News(int id, String description, Date creation_date) {
        this.id = id;
        this.description = description;
        this.creation_date = creation_date;
    }
    
    

    public News(String title, String bg_img, String img, String description, Date creation_date) {
        this.title = title;
        this.bg_img = bg_img;
        this.img = img;
        this.description = description;
        this.creation_date = creation_date;
    }

    public News(int id, String title, String img, String description, Date creation_date) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.description = description;
        this.creation_date = creation_date;
    }

 

    public String getImg_bg() {
        return bg_img;
    }

    public void setImg_bg(String bg_img) {
        this.bg_img = bg_img;
    }


    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return title;
    }

    public void setTitre(String title) {
        this.title = title;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return creation_date;
    }

    public void setDate_creation(Date creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "actualite{" + "id=" + id + ", Titre=" + title + ", img_bg=" + bg_img + ", img=" + img + ", description=" + description + ", date_creation=" + creation_date + '}';
    }

    public void setDate_creation(DatePicker date_creation) {
    }

    
    

}
