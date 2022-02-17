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
public class Game_player {
    int id_game_player;
    int id_game;
    int id_player;

    public int getId_game_player() {
        return id_game_player;
    }

    public int getId_game() {
        return id_game;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_game_player(int id_game_player) {
        this.id_game_player = id_game_player;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public Game_player(int id_game_player, int id_game, int id_player) {
        this.id_game_player = id_game_player;
        this.id_game = id_game;
        this.id_player = id_player;
    }

    public Game_player(int id_game, int id_player) {
        this.id_game = id_game;
        this.id_player = id_player;
    }
    
    
}
