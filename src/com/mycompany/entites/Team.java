/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;


public class Team {
    int id_team;
String team_name;
String logo;
double players_number;
String description;

    @Override
    public String toString() {
        return "Team{" + "id_team=" + id_team + ", team_name=" + team_name + ", logo=" + logo + ", players_number=" + players_number + ", description=" + description + '}';
    }

    public Team(String team_name, String logo, int players_number, String description) {
        this.team_name = team_name;
        this.logo = logo;
        this.players_number = players_number;
        this.description = description;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public void setName(String team_name) {
        this.team_name = team_name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setNb_joueur(double players_number) {
        this.players_number = players_number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_team() {
        return id_team;
    }

    public String getName() {
        return team_name;
    }

    public String getLogo() {
        return logo;
    }

    public double getNb_joueur() {
        return players_number;
    }

    public String getDescription() {
        return description;
    }

    public Team() {
        
    }


    public Team(int id_team, String team_name, String logo, int players_number, String description) {
        this.id_team = id_team;
        this.team_name = team_name;
        this.logo = logo;
        this.players_number = players_number;
        this.description = description;
    }

    
}
