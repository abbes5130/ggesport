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
      
        TeamCRUD tcr = new TeamCRUD();
        Team t1 = new Team(1,"Tec9","https", 11, "equipe tres competant depuis 2009");
        //tcr.create(t1);
        //tcr.update(t1);
        tcr.Retrieve();
       

    }
    
}
