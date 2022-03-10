/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ridha
 */
public class MyConnexion {
    private final String url = "jdbc:mysql://localhost:3306/ggesport";
    private final String user = "root";
    private final String password ="root";
    private Connection conncetion;
    static MyConnexion instance;
    
    
     private MyConnexion(){
        
        try {
            conncetion = DriverManager.getConnection(url, user, password);
            System.out.println("conncetion etablie");
     
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              
    }
    
    public static MyConnexion getInstance(){
       if(instance == null)
           instance = new MyConnexion();
       return instance;
    }

    public Connection getConncetion() {
        return conncetion;
    }
    
}
 