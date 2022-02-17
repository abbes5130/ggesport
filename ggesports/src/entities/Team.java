/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author mohamedabbes
 */
public class Team {
    int id_team;
String name;
String logo;
int nb_joueur;
String description;

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setNb_joueur(int nb_joueur) {
        this.nb_joueur = nb_joueur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_team() {
        return id_team;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public int getNb_joueur() {
        return nb_joueur;
    }

    public String getDescription() {
        return description;
    }

    public Team() {
        
    }

    public Team(String name, String logo, int nb_joueur, String description) {
        this.name = name;
        this.logo = logo;
        this.nb_joueur = nb_joueur;
        this.description = description;
    }

    public Team(int id_team, String name, String logo, int nb_joueur, String description) {
        this.id_team = id_team;
        this.name = name;
        this.logo = logo;
        this.nb_joueur = nb_joueur;
        this.description = description;
    }

    
}
