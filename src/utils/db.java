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
 * @author mohamedabbes
 */
public class db {
    public String url = "jdbc:mysql://localhost:3306/ggtest";
    public String username = "root";
    public String pwd = "";
   static Connection  cnx;
    public static db instance;
    
    public db(){
    try {
    cnx = DriverManager.getConnection(url,username,pwd);
    System.out.println("cnx done");
    } catch (SQLException ex)
    {
    System.out.println("error in connecting to db");
    }
    }
   public static Connection getCnx(){
   return cnx;
   }
    public static db getInstance(){
    if(instance == null)
    {
    instance = new db();
    }
    return instance;
    }
}
