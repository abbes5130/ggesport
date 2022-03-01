/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mohamedabbes
 *
 */
public class Game {
    int id_game;
    String game_name;
    String logo;

    public Game() {
    }

    public Game(int id_game, String game_name, String logo) {
        this.id_game = id_game;
        this.game_name = game_name;
        this.logo = logo;
    }

    public Game(String game_name, String logo) {
        this.game_name = game_name;
        this.logo = logo;
    }


    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId_game() {
        return id_game;
    }

    public String getGame_name() {
        return game_name;
    }

    public String getLogo() {
        return logo;
    }
    
    
}
