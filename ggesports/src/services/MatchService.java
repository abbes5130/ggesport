/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyDB;
import java.util.List;
import entities.Match;
import entities.MatchResultWithTeam;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;


/**
 *
 * @author Ghassene
 */
public class MatchService implements IService<Match> {
    
    Connection cnx2;
    public MatchService() {
        cnx2 = MyDB.getInstance().getCnx();
    }
    
    

    
    public int CreateMatch(Match t) {
       String req = "insert into matchs (time,date,location,nb_place_dispo,price,link)"
                   +"values(?, ?, ?, ?, ?, ?)";
       PreparedStatement statement;
        try {
            statement = cnx2.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            statement.setTime(1, (Time) t.getTime());
            statement.setDate(2, (Date) t.getDate());
            statement.setString(3,t.getLocation());
            statement.setInt(4,t.getNb_seats());
            statement.setInt(5,t.getPrice());
            statement.setString(6,t.getLink());
            statement.executeUpdate();

            System.out.println("Match created");
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                    
        }
        return -1;
    }

    @Override
    public void Update(Match t) {
            String req = "UPDATE matchs SET time=?, date=?, location=?,price=?, nb_place_dispo=?, link=? WHERE id_match=?";
            PreparedStatement statement;

        try {
            statement = cnx2.prepareStatement(req);
            statement.setTime(1, (Time) t.getTime());
            statement.setDate(2, (Date) t.getDate());
            statement.setString(3,t.getLocation());
            statement.setInt(4,t.getPrice());
            statement.setInt(5,t.getNb_seats());
            statement.setString(6,t.getLink());
            statement.setInt(7,t.getId_match());
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println(statement);
            statement.executeUpdate();
            
            System.out.println("Match updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Delete(Match t) {
        
            String req = "DELETE FROM matchs WHERE id_match=?";
            PreparedStatement statement;

        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1,t.getId_match());
            statement.executeUpdate();

            System.out.println("Match deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
    }

    @Override
    public List<Match> Retrieve() {
         List<Match> ListMatch = new ArrayList<Match>();
        
        try {
            Statement statement;
            statement = cnx2.createStatement();
            String req = "Select * From matchs";
            ResultSet rst;
            rst= statement.executeQuery(req);
            
           
            while(rst.next()){
                Match t = new Match();
                
                t.setId_match(rst.getInt(1));
                t.setTime(rst.getTime("time"));
                t.setDate(rst.getDate("date"));
                t.setLocation(rst.getString("location"));
                t.setNb_seats(rst.getInt("nb_place_dispo"));
                t.setNb_seats(rst.getInt("price"));
                t.setLink(rst.getString("link"));
                ListMatch.add(t);
                
                    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListMatch;
    }

    @Override
    public void Create(Match t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MatchResultWithTeam> RetrieveWithTeams() {
    
            List<MatchResultWithTeam> ListMatchWithTeam = new ArrayList<MatchResultWithTeam>();
                try {
            Statement statement;
            statement = cnx2.createStatement();
            String req= "SELECT matchs.id_match, matchs.time, matchs.date,equipe.nom,equipe.logo from matchs, equipe JOIN match_equipe WHERE matchs.id_match = match_equipe.id_match AND equipe.id_equipe = match_equipe.id_equipe ORDER BY matchs.date, matchs.time, `matchs`.`id_match` ASC;";
            ResultSet rst;
            rst= statement.executeQuery(req);
            
           
            while(rst.next()){
                MatchResultWithTeam mwt = new MatchResultWithTeam(
                        rst.getInt("id_match"),
                        rst.getTime("time"),
                        rst.getDate("date"),
                        rst.getString("nom"),
                        rst.getString("logo")
                );
                
               ListMatchWithTeam.add(mwt);
                
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ListMatchWithTeam;
    }
}
    
