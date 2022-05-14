/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Ghassene
 */
public class Reservation {
    private int id_ticket;
    private int id_match;
    private int id_user;
    private String firstname;
    private String lastname;
    private Date date;
    private Time time;
    private String location;
    private int price;

    public Reservation() {
    }

    public Reservation(int id_match, int id_user, String firstname, String lastname, Date date, Time time, String location, int price) {
        this.id_match = id_match;
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.date = date;
        this.time = time;
        this.location = location;
        this.price = price;
    }

    public Reservation(int id_ticket, int id_match, int id_user, String firstname, String lastname, Date date, Time time, String location, int price) {
        this.id_ticket = id_ticket;
        this.id_match = id_match;
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.date = date;
        this.time = time;
        this.location = location;
        this.price = price;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Reseveratataettion numero "+id_ticket+"\n"+
               "Match num : "+id_match+"\n";
    }

  
 
 
    
    
    
}
