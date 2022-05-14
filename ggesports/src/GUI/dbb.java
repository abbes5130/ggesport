/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mohamedabbes
 */
public class dbb {
    public static Connection getConnection() throws SQLException {
        
   
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ggesportt","root","root");
    System.out.println("cnx okkkkkk");
    return connection;
    }
}
