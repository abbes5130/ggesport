/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mohamedabbes
 */
public class Player {
    int id_player ;
    int id_team;
    String name;
    String surname;
    String description;
    String tag;
    String photo;

    public Player() {
    }

    public Player(int id_player, int id_team, String name, String surname, String description, String tag, String photo) {
        this.id_player = id_player;
        this.id_team = id_team;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.tag = tag;
        this.photo = photo;
    }

    public int getId_player() {
        return id_player;
    }

    public int getId_team() {
        return id_team;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Player(int id_team, String name, String surname, String description, String tag, String photo) {
        this.id_team = id_team;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.tag = tag;
        this.photo = photo;
    }
    
    
}
