/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Reservation;
import utils.MyDB;
import services.MatchService;
import services.ReservationService;

/**
 *
 * @author Ghassene
 */
public class Main {
      public static void main(String[] args) {
          MyDB db = MyDB.getInstance();
          MatchService msr= new MatchService();
          ReservationService rsv = new ReservationService();
          Reservation reservation = new Reservation();
          //Match match = new Match(Time.valueOf("16:30:00"),Date.valueOf("2020-06-06"),"sfax",200,"lien");
          //msr.ajouter(match);
          rsv.CreateRes(reservation,3,2);
          System.out.println("reservation added");
    }        
           
           
    
    
}