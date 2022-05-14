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
String req = "INSERT INTO matches (id_Team_1,id_Team_2, time, date, location, nb_seats, price, link) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";      
PreparedStatement statement;
        try {
            
            statement = cnx2.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,t.getIdTeam1());
            statement.setInt(2,t.getIdTeam2());
            statement.setTime(3, (Time) t.getTime());
            statement.setDate(4, (Date) t.getDate());
            statement.setString(5,t.getLocation());
            statement.setInt(6,t.getNb_seats());
            statement.setInt(7,t.getPrice());
            statement.setString(8,t.getLink());
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
            String req = "UPDATE matches SET id_Team_1=?, id_Team_2=?, time=?, date=?, location=?,price=?, nb_seats=?, link=? WHERE id_match=?";
            PreparedStatement statement;

        try {
            statement = cnx2.prepareStatement(req);
            
            statement.setInt(1,t.getIdTeam1());
            statement.setInt(2,t.getIdTeam2());
            statement.setTime(3, (Time) t.getTime());
            statement.setDate(4, (Date) t.getDate());
            statement.setString(5,t.getLocation());
            statement.setInt(6,t.getNb_seats());
            statement.setInt(7,t.getPrice());
            statement.setString(8,t.getLink());
            statement.setInt(9,t.getId_match());
            
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
        
            String req = "DELETE FROM matches WHERE id_match=?";
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
            String req = "Select * From matches";
            ResultSet rst;
            rst= statement.executeQuery(req);
            
           
            while(rst.next()){
                Match t = new Match();
                
                t.setId_match(rst.getInt(1));
                t.setTime(rst.getTime("time"));
                t.setDate(rst.getDate("date"));
                t.setLocation(rst.getString("location"));
                t.setNb_seats(rst.getInt("nb_seats"));
                t.setNb_seats(rst.getInt("price"));
                t.setLink(rst.getString("link"));
                t.setIdTeam1(rst.getInt("id_Team_1"));
                t.setIdTeam2(rst.getInt("id_Team_2"));
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
            String req= "SELECT matches.id_match, matches.id_Team_1,matches.id_Team_2,matches.time,matches.date,matches.location,matches.nb_seats,matches.price,matches.link,team.team_name, team.logo FROM `matches`JOIN team ORDER BY matches.date, matches.time, `matches`.`id_match` ASC;";
            ResultSet rst;
            rst= statement.executeQuery(req);
            
           
            while(rst.next()){
                MatchResultWithTeam mwt = new MatchResultWithTeam(
                        rst.getInt("id_match"),
                        rst.getTime("time"),
                        rst.getDate("date"),
                        rst.getString("team_name"),
                        rst.getString("logo")
                );
                
               ListMatchWithTeam.add(mwt);
                
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ListMatchWithTeam;
    }

    @Override
    public void create(Match t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Match t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Match obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Match obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Match> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
