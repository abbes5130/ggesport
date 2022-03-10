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
 * @author DeadlyDaggerS
 */
public class MatchResultWithTeam {
    private int id_match;
    private Time time;
    private Date date;
    private String nom_equipe;
    private String logo_equipe;

    public MatchResultWithTeam(int id_match, Time time, Date date, String nom_equipe, String logo_equipe) {
        this.id_match = id_match;
        this.time = time;
        this.date = date;
        this.nom_equipe = nom_equipe;
        this.logo_equipe = logo_equipe;
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

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public String getLogo_equipe() {
        return logo_equipe;
    }

    public void setLogo_equipe(String logo_equipe) {
        this.logo_equipe = logo_equipe;
    }

    @Override
    public String toString() {
        return "MatchResultWithTeam{" + "id_match=" + id_match + ", time=" + time + ", date=" + date + ", nom_equipe=" + nom_equipe + ", logo_equipe=" + logo_equipe + '}';
    }
    
    
}
