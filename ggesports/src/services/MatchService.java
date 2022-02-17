/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyDB;
import java.util.List;
import entities.Match;
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
    
    

    
    
    public void CreateMatch(Match t, int ide1, int ide2) {
       String req = "insert into Matchs (id_equipe1,id_equipe_2,time,date,location,nb_place_dispo,link)"
                   +"values((Select e.Id_equipe =?), (Select e.Id_equipe =?), ?, ?, ?, ?, ?)";
       PreparedStatement statement;
        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1, ide1);
            statement.setInt(2, ide2);
            statement.setTime(3, (Time) t.getTime());
            statement.setDate(4, (Date) t.getDate());
            statement.setString(5,t.getLocation());
            statement.setInt(6,t.getNb_place_dispo());
            statement.setString(7,t.getLink());
            statement.executeUpdate();

            System.out.println("Match created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                    
        }
               
    }

    
    public void UpdateMatch(Match t,int ide1, int ide2) {
            String req = "UPDATE matchs SET id_equipe_1=(Select e.Id_equipe =? from equipe e), id_equipe_2=(Select e.Id_equipe =? from equipe e), time=?, date=?, location=?, nb_place_dispo=?, link=? WHERE id_match=?";
            PreparedStatement statement;

        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1,ide1);
            statement.setInt(2,ide2);
            statement.setTime(3, (Time) t.getTime());
            statement.setDate(4, (Date) t.getDate());
            statement.setString(5,t.getLocation());
            statement.setInt(6,t.getNb_place_dispo());
            statement.setString(7,t.getLink());
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
            String req = "Select * From Matchs";
            ResultSet rst;
            rst= statement.executeQuery(req);
            
           
            while(rst.next()){
                Match t = new Match();
                
                t.setId_match(rst.getInt(1));
                t.setId_equipe_1(rst.getInt(2));
                t.setId_equipe_2(rst.getInt(3));
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
    }

    @Override
    public void Create(Match t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Match t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
