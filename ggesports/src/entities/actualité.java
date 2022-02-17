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
public class actualité {

    private int id;
    private String Titre;
    private String img_bg;
    private String img;
    private String description;
    private Date date_creation;

    public actualité(int id, String Titre, String img_bg, String img, String description, Date date_creation) {
        this.id = id;
        this.Titre = Titre;
        this.img_bg = img_bg;
        this.img = img;
        this.description = description;
        this.date_creation = date_creation;
    }

    public actualité(int id) {
        this.id = id;
    }

    public actualité(String Titre, String img_bg, String img, String description, Date date_creation) {
        this.Titre = Titre;
        this.img_bg = img_bg;
        this.img = img;
        this.description = description;
        this.date_creation = date_creation;
    }

    public String getImg_bg() {
        return img_bg;
    }

    public void setImg_bg(String img_bg) {
        this.img_bg = img_bg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public actualité() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "actualite{" + "id=" + id + ", Titre=" + Titre + ", img_bg=" + img_bg + ", img=" + img + ", description=" + description + ", date_creation=" + date_creation + '}';
    }

    

}
