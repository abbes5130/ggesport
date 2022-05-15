/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DeadlyDaggerS
 */
public class Match_Team {
    
    private int id_match_team;
    private int id_match;
    private int id_team;

    public Match_Team() {
    }

    public Match_Team(int id_match, int id_team) {
        this.id_match = id_match;
        this.id_team = id_team;
    }

    public Match_Team(int id_match_team, int id_match, int id_team) {
        this.id_match_team = id_match_team;
        this.id_match = id_match;
        this.id_team = id_team;
    }

    public int getId_match_team() {
        return id_match_team;
    }

    public void setId_match_team(int id_match_team) {
        this.id_match_team = id_match_team;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }
    
    
}
