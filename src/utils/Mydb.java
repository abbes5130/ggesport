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
 * @author khale
 */
public class Mydb {
     private final String url = "jdbc:mysql://localhost:3306/ggesports";
    private final String user = "root";
    private final String password ="";
    private Connection conncetion;
    static Mydb instance;
    
    
    private Mydb(){
        
        try {
            conncetion = DriverManager.getConnection(url, user, password);
            System.out.println("conncetion etablie");
     
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              
    }
    
    public static Mydb getInstance(){
       if(instance == null)
           instance = new Mydb();
       return instance;
    }

    public Connection getConncetion() {
        return conncetion;
    }
}
