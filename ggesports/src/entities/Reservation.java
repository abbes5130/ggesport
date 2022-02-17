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
    private int id_utilisateur;
    private String nom_utilisateur;
    private String prenom_utilisateur;
    private Date date;
    private Time time;
    private String location;

    public Reservation() {
    }

    public Reservation(int id_match, int id_utilisateur) {
        this.id_match = id_match;
        this.id_utilisateur = id_utilisateur;
    }
    
    

    public Reservation(int id_match, int id_utilisateur, String nom_utilisateur, String prenom_utilisateur, Date date, Time time, String location) {
        this.id_match = id_match;
        this.id_utilisateur = id_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public Reservation(int id_ticket, int id_match, int id_utilisateur, String nom_utilisateur, String prenom_utilisateur, Date date, Time time, String location) {
        this.id_ticket = id_ticket;
        this.id_match = id_match;
        this.id_utilisateur = id_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.date = date;
        this.time = time;
        this.location = location;
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

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
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

    @Override
    public String toString() {
        return "Reservation{" + "id_ticket=" + id_ticket + ", id_match=" + id_match + ", id_utilisateur=" + id_utilisateur + ", nom_utilisateur=" + nom_utilisateur + ", prenom_utilisateur=" + prenom_utilisateur + ", date=" + date + ", time=" + time + ", location=" + location + '}';
    }
    
    
    
    
    
}
