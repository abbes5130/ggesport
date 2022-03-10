/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Match;
import utils.MyDB;

import services.Match_Team_Service;

public class Ggesports {


    public static void main(String[] args) {


        MyDB db = MyDB.getInstance();
        Match match = new Match();
        Match_Team_Service mts = new Match_Team_Service();
        
        mts.Update(3,2,1);
        System.out.println("reservation added");



    }

    /**
     *
     * @param args
     */

    
    
}
