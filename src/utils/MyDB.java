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
 * @author Ghassene
 */
public class MyDB {
    private final String url = "jdbc:mysql://localhost:3306/ggtest";
    private final String user = "root";
    private final String password = "";
    private Connection connection;
    public static MyDB instance;
    
    public MyDB(){
    
        try {
            connection =DriverManager.getConnection(url, user, password);
             System.out.println("connection etablie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getCnx(){
    return connection;
    }
    
    public static MyDB getInstance(){
    
    if(instance ==null){
    instance = new MyDB();
    }
    return instance;
    }
}
