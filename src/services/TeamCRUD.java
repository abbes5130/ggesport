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
import java.util.ArrayList;
import java.util.List;
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
    public  List<Team> Retrieve() {
String sql = "SELECT * FROM team";
 List<Team> listTeam = new ArrayList<Team>();
Statement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);


 
while (result.next()){
    Team t = new Team();
    t.setId_team( result.getInt("id_team"));
    t.setName(result.getString("team_name"));
    t.setLogo(result.getString("logo"));
    t.setNb_joueur(result.getInt("players_number"));
    t.setDescription(result.getString("description"));

    listTeam.add(t);
    
}
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTeam;
    }    

    @Override
    public void Create(Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Team obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Team obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Team> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
