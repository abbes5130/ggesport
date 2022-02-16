/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Ghassene
 */
public class Match {
    private int id_match;
    private Time time;
    private Date date;
    private String location;
    private int nb_place_dispo;
    private String link;

    public Match() {
    }

    public Match(int id_match, Time time, Date date, String location, int nb_place_dispo, String link) {
        this.id_match = id_match;
        this.time = time;
        this.date = date;
        this.location = location;
        this.nb_place_dispo = nb_place_dispo;
        this.link = link;
    }

    public Match(Time time, Date date, String location, int nb_place_dispo, String link) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.nb_place_dispo = nb_place_dispo;
        this.link = link;
    }
    

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNb_place_dispo() {
        return nb_place_dispo;
    }

    public void setNb_place_dispo(int nb_place_dispo) {
        this.nb_place_dispo = nb_place_dispo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Match{" + "id_match=" + id_match + ", time=" + time + ", date=" + date + ", location=" + location + ", nb_place_dispo=" + nb_place_dispo + ", link=" + link + '}';
    }
    
    
    
}
