/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.db;
import entities.Player;
import entities.Team;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamedabbes
 */
public class PlayerCRUD implements IService<Player>{
   
@Override
    public void create(Player t) {
        String sql = "INSERT INTO player (id_team, firstname, lastname, description, player_tag, photo) VALUES (?, ?, ?, ?, ?, ?)";
 
PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);

            statement.setInt(1,t.getId_team());
            
statement.setString(2,t.getName());
statement.setString(3, t.getSurname());
statement.setString(4,t.getDescription());
statement.setString(5, t.getTag());
statement.setString(6, t.getPhoto());
       statement.executeUpdate();
       System.out.println("created new Player");
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
          



 
    }

    @Override
    public void delete(int t) {
                String sql = "delete from player where id_player = ?";

    
        PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
statement.setInt(1,t);
      // execute the preparedstatement
      statement.executeUpdate();
             System.out.println("Deleted Player");

      
        }catch(SQLException ex){System.out.println(ex.getMessage());
      
    }
    }
    @Override
    public void update(Player t) {
String sql = "UPDATE player SET id_player=?, firstname=?, lastname=?, description=?, player_tag=?, photo=? WHERE id_player=?";
         PreparedStatement statement;

        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            statement.setInt(1,t.getId_team());
            
statement.setString(2,t.getName());
statement.setString(3, t.getSurname());
statement.setString(4,t.getDescription());
statement.setString(5, t.getTag());
statement.setString(6, t.getPhoto());
statement.setInt(7, t.getId_player());
statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String Retrieve() {
String sql = "SELECT * FROM player";
 
Statement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);

int count = 0;
 
while (result.next()){
     int id_player = result.getInt(1) ;
    int id_team = result.getInt(2);
    String name = result.getString(3);
    String surname = result.getString(4);
    String description = result.getString(5);
    String tag = result.getString(6);
    String photo = result.getString(7);
    String output = "Player #%d: %s - %s - %s - %s - %s - %s - %s";
    System.out.println(String.format(output, ++count, id_player, id_team, name, surname, description, tag, photo));
    
    
}
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "d";
    }    

    
}
