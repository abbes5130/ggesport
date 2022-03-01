/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.db;

import entities.Game;
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
public class TeamCRUD implements IService<Team>{
    
@Override
    public void create(Team t) {
        String sql = "INSERT INTO team (team_name, logo, players_number, description) VALUES (?, ?, ?, ?)";
 
PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);

            statement.setString(1,t.getName());
            
statement.setString(2,t.getLogo());
statement.setInt(3, t.getNb_joueur());
statement.setString(4,t.getDescription());
       statement.executeUpdate();
       System.out.println("created new team");
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
          



 
    }

    @Override
    public void delete(int t) {
                String sql = "delete from team where id_team = ?";

    
        PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
statement.setInt(1,t);
      // execute the preparedstatement
      statement.executeUpdate();
             System.out.println("Deleted team");

      
        }catch(SQLException ex){System.out.println(ex.getMessage());
      
    }
    }
    @Override
    public void update(Team t) {
String sql = "UPDATE team SET team_name=?, logo=?, players_number=?, description=? WHERE id_team=?";
         PreparedStatement statement;

        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
 statement.setString(1,t.getName());
            
statement.setString(2,t.getLogo());
statement.setInt(3, t.getNb_joueur());
statement.setString(4,t.getDescription());

statement.setInt(5,t.getId_team());

statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String Retrieve() {
String sql = "SELECT * FROM team";
 
Statement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);

int count = 0;
 
while (result.next()){
    int id_equipe = result.getInt(1);
    String nom_equipe = result.getString(2);
    String logo = result.getString(3);
    int nb_joueur = result.getInt(4);
    String description = result.getString(5);
    

 
    String output = "Team #%d: %s - %s - %s - %s - %s";
    System.out.println(String.format(output, ++count, id_equipe, nom_equipe, logo, nb_joueur, description));
    
    
}
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "d";
    }    


}
