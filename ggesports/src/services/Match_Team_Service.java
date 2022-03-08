/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Match;
import entities.Match_Team;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author DeadlyDaggerS
 */
public class Match_Team_Service implements IService<Match_Team> {

    Connection cnx2;

    public Match_Team_Service() {
        cnx2 = MyDB.getInstance().getCnx();
    }

    public void Create(int match_id, int team_id) {
        String req = "insert into match_equipe (id_match,id_equipe)"
                + "values(?,?)";
        PreparedStatement statement;

        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1, match_id);
            statement.setInt(2, team_id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
 

    
    public void Delete(int id_match_team) {
        
    String req = "DELETE FROM match_equipe WHERE id_match_equipe=?";
            PreparedStatement statement;
            
     try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1,id_match_team);
            statement.executeUpdate();

            System.out.println("Match deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
       public void DeleteMatchTeam(int id_match) {
        
    String req = "DELETE FROM match_equipe WHERE id_match=?";
            PreparedStatement statement;
            
     try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1,id_match);
            statement.executeUpdate();

            System.out.println("Match deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

    
        public void Update(int id_match_team,int id_match, int id_team) 
        {
            String req = "UPDATE match_equipe SET id_match=?, id_equipe =? WHERE id_match_equipe = ?";
            PreparedStatement statement;
            
            try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1,id_match);
            statement.setInt(2, id_team);
            statement.setInt(3,id_match_team);
            System.out.println("Match updated");
             statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


            
    }

    @Override
    public void Update(Match_Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Match_Team> Retrieve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Create(Match_Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Match_Team t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
