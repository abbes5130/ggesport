/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class connexion {
   public String url="jdbc:mysql://localhost:3306/ggesport";
   public String login="root";
   public String pwd="";
Connection cnx; 
public static connexion instance;
        private connexion(){
       try {
           cnx=DriverManager.getConnection(url, login, pwd);
           System.out.println("connexion etablie");
       } catch (SQLException ex) {
System.err.println(ex.getMessage());       }
   
    
   
    }
               public Connection getcnx(){
                   return cnx;
               }    
               public static connexion getIstance(){
                   if(instance==null){
                       instance = new connexion();
                       
                       
                   }
                   return instance;
               }
       }



