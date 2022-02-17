/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.db;
import entities.Game;
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
public class GameCRUD implements IService<Game>{

    @Override
    public void create(Game t) {
        String sql = "INSERT INTO game (game_name, logo) VALUES (?, ?)";
 
PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);

            statement.setString(1,t.getGame_name());
statement.setString(2,t.getLogo());
       statement.executeUpdate();
       System.out.println("created new game");
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
          



 
    }

    @Override
    public void delete(int t) {
                String sql = "delete from game where id_game = ?";

    
        PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
statement.setInt(1,t);
      // execute the preparedstatement
      statement.executeUpdate();
             System.out.println("Deleted game");

      
        }catch(SQLException ex){System.out.println(ex.getMessage());
      
    }
    }
    @Override
    public void update(Game t) {
String sql = "UPDATE game SET game_name=?, logo=? WHERE id_game=?";
         PreparedStatement statement;

        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            statement.setString(1, t.getGame_name());
statement.setString(2, t.getLogo());
statement.setInt(3, t.getId_game());
statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String Retrieve() {
String sql = "SELECT * FROM game";
 
Statement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);

int count = 0;
 
while (result.next()){
    int id_game = result.getInt(1);
    String game_name = result.getString(2);
    String logo = result.getString(3);

 
    String output = "Game #%d: %s - %s - %s";
    System.out.println(String.format(output, ++count, id_game, game_name, logo));
    
    
}
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "d";
    }}
    