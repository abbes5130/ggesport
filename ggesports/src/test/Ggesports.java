/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

<<<<<<< HEAD
import entities.Match;
import utils.MyDB;

import services.Match_Team_Service;

/**
 *
 * @author Ghassene
=======

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
>>>>>>> origin/Gestion_team
 */
public class Ggesports {


    public static void main(String[] args) {

<<<<<<< HEAD
        MyDB db = MyDB.getInstance();
        Match match = new Match();
        Match_Team_Service mts = new Match_Team_Service();
        
        mts.Update(3,2,1);
        System.out.println("reservation added");
=======
        // TODO code application logic here
        db test = new db();
      
        TeamCRUD tcr = new TeamCRUD();
        Team t1 = new Team(1,"Tec9","https", 11, "equipe tres competant depuis 2009");
        //tcr.create(t1);
        //tcr.update(t1);
        tcr.Retrieve();
       
>>>>>>> origin/Gestion_team

    }

    /**
     *
     * @param args
     */

    
    
}
