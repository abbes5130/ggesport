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
    private int nb_seats;
    private String link;

    public Match() {
    }

    public Match(Time time, Date date, String location, int nb_seats, String link) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.nb_seats = nb_seats;
        this.link = link;
    }

    public Match(int id_match, Time time, Date date, String location, int nb_seats, String link) {
        this.id_match = id_match;
        this.time = time;
        this.date = date;
        this.location = location;
        this.nb_seats = nb_seats;
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

    public int getNb_seats() {
        return nb_seats;
    }

    public void setNb_seats(int nb_seats) {
        this.nb_seats = nb_seats;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Match{" + "id_match=" + id_match + ", time=" + time + ", date=" + date + ", location=" + location + ", nb_seats=" + nb_seats + ", link=" + link + '}';
    }

    
    
    
    
}
