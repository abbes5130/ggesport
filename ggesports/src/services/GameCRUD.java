/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import utils.db;

import entities.Game;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
   public  List<Game> Retrieve() {
String sql = "SELECT * FROM game";
 List<Game> listGame = new ArrayList<Game>();
Statement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);

 
while (result.next()){
    Game g = new Game();
    g.setId_game(result.getInt(1));
    g.setGame_name(result.getString(2));
    g.setLogo(result.getString(3));

 listGame.add(g);
    
    
}
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGame;
    }

    @Override
    public void Create(Game t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Game t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Game t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    