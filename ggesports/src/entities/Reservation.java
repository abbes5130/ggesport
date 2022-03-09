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

    public Reservation() {
    }

    public Reservation(int id_match, int id_user) {
        this.id_match = id_match;
        this.id_user = id_user;
    }

    public Reservation(int id_ticket, int id_match, int id_user) {
        this.id_ticket = id_ticket;
        this.id_match = id_match;
        this.id_user = id_user;
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

    @Override
    public String toString() {
        return "Reservation{" + "id_ticket=" + id_ticket + ", id_match=" + id_match + ", id_user=" + id_user + '}';
    }


 
    
    
    
}
