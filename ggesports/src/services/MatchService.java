/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyDB;
import java.util.List;
import entities.Match;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ghassene
 */
public class MatchService implements IService<Match> {

    
    @Override
    public void ajouter(Match t) {
       String req = "insert into Matchs (time,date,location,nb_place_dispo,link)"
                   +"values(?, ?, ?, ?, ?)";
       PreparedStatement statement;
        try {
            statement = (PreparedStatement) new MyDB().getCnx().prepareStatement(req);
            statement.setTime(1, (Time) t.getTime());
            statement.setDate(2, (Date) t.getDate());
            statement.setString(3,t.getLocation());
            statement.setInt(4,t.getNb_place_dispo());
            statement.setString(5,t.getLink());
            statement.executeUpdate();

            System.out.println("Match created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                    
        }
               
    }

    @Override
    public void modifier(Match t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Match t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Match> rechercher(Match t) {
         List<Match> ListMatch = new ArrayList<Match>();
        
        try {
            Statement statement;
            statement = new MyDB().getCnx().createStatement();
            String req = "Select * From Matchs";
            ResultSet rst;
            rst= statement.executeQuery(req);
            
           
            while(rst.next()){
                
                t.setId_match(rst.getInt(1));
                t.setTime(rst.getTime("time"));
                t.setDate(rst.getDate("date"));
                t.setLocation(rst.getString("location"));
                t.setNb_place_dispo(rst.getInt("nb_place_dispo"));
                t.setLink(rst.getString("link"));
                ListMatch.add(t);
                
                    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListMatch;
    }}
    
