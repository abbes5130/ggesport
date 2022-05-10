
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class News {
    //nemchio taw nchofo entity fi symfony
    
      private int id;

    public News(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public News(int id, String title, String bg_img, String img, String description) {
        this.id = id;
        this.title = title;
        this.bg_img = bg_img;
        this.img = img;
        this.description = description;
    }
    private String title;
    private String bg_img;
    private String img;
    private String creation_date;
    private String description;

    public News(String title, String bg_img, String img, String description) {
        this.title = title;
        this.bg_img = bg_img;
        this.img = img;
        this.description = description;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public News(int id, String title, String bg_img, String img, String creation_date, String description) {
        this.id = id;
        this.title = title;
        this.bg_img = bg_img;
        this.img = img;
        this.creation_date = creation_date;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBg_img() {
        return bg_img;
    }

    public void setBg_img(String bg_img) {
        this.bg_img = bg_img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", title=" + title + ", bg_img=" + bg_img + ", img=" + img + ", creation_date=" + creation_date + ", description=" + description + '}';
    }

    
    
    
   
    
    
    
    
    }
