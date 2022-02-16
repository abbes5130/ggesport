/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import utils.MyDB;
import entities.Match;
import java.sql.Date;
import java.sql.Time;
import services.MatchService;

/**
 *
 * @author Ghassene
 */
public class Main {
      public static void main(String[] args) {
          MyDB db = new MyDB();
          MatchService msr= new MatchService();
          Match match = new Match();
          //Match match = new Match(Time.valueOf("16:30:00"),Date.valueOf("2020-06-06"),"sfax",200,"lien");
          System.out.println(msr.rechercher(match));
    }        
           
           
    
    
}
