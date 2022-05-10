/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.entities;

/**
 *
 * @author Ghassene Badra
 */
public class ComboTeam {
    
    private int idTeam;
    private String teamName;

    public ComboTeam(int idTeam, String teamName) {
        this.idTeam = idTeam;
        this.teamName = teamName;
    }

    public ComboTeam() {
    }


    @Override
    public String toString() {
        return this.teamName;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    
    
}
