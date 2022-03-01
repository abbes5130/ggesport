/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Game;
import entities.Player;
import entities.Team;
import services.GameCRUD;
import services.PlayerCRUD;
import services.TeamCRUD;

import utils.db;


/**
 *
 * @author mohamedabbes
 */
public class Ggesports {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        db test = new db();
        GameCRUD gcr = new GameCRUD();

        Game g1 = new Game(2,"Valorant","7");
       //gcr.create(g1);
       // gcr.delete(1);
      //gcr.update(g1);
        gcr.Retrieve();
        TeamCRUD tcr = new TeamCRUD();
        Team t1 = new Team(1,"Tec9","https", 11, "equipe tres competant depuis 2009");
        //tcr.create(t1);
        //tcr.update(t1);
        tcr.Retrieve();
        PlayerCRUD pcr = new PlayerCRUD();
        Player p1 = new Player(2,"abbes","mohamed","h","sav0n","https");
        //pcr.create(p1);
        pcr.Retrieve();

        //Game g1 = new Game("Dota2","2");
      // gcr.create(g1);
       // gcr.delete(1);
      // gcr.update(g1);
        gcr.Retrieve();

       // Team t1 = new Team("Tec9","https", 11, "equipe tres competenet depuis 2022");
       // tcr.create(t1);
        tcr.Retrieve();
        //PlayerCRUD pcr = new PlayerCRUD();
        //Player p1 = new Player(2,"abbes","mohamed","h","sav0n","https");
        //pcr.create(p1);
        //pcr.Retrieve();

    }
    
}
