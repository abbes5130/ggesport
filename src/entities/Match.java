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

    public int getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(int idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public int getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(int idTeam2) {
        this.idTeam2 = idTeam2;
    }
    private int price;
    private String link;
    private int idTeam1,idTeam2;

    public Match() {
    }

    public Match( int idTeam1, int idTeam2,Time time, Date date, String location, int nb_seats,int price, String link) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.nb_seats = nb_seats;
        this.price = price;
        this.link = link;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
    }
    
    
        public Match(int id_match, int idTeam1, int idTeam2,Time time, Date date, String location, int nb_seats,int price, String link) {
        this.id_match = id_match;
        this.time = time;
        this.date = date;
        this.location = location;
        this.nb_seats = nb_seats;
        this.price = price;
        this.link = link;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
    }
    

    public Match(int id_match, Time time, Date date, String location, int nb_seats,int price, String link) {
        this.id_match = id_match;
        this.time = time;
        this.date = date;
        this.location = location;
        this.nb_seats = nb_seats;
        this.price = price;
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
    
        public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Match{" + "id_match=" + id_match + ", time=" + time + ", date=" + date + ", location=" + location + ", nb_seats=" + nb_seats +", price=" + price + ", link=" + link + '}';
    }

    
    
    
    
}
