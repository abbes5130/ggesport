/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.MyDB;

/**
 *
 * @author DeadlyDaggerS
 */
public class ReservationService implements IService<Reservation>{
    
    
        Connection cnx2;
    public ReservationService() {
        cnx2 = MyDB.getInstance().getCnx();
    }

    
    public void CreateRes(Reservation t, int idu, int idm) {
                        String req = "insert into reservation (id_utilisateur, "
                        + "id_match,nom_utilisateur, prenom_utilisateur, time, date, location) "
                        + "SELECT u.Id_utilisateur, m.id_match, u.Nom, u.Prenom, m.time, m.date, m.location "
                        + "from utilisateurs u, matchs m where u.Id_utilisateur=? and m.id_match=?" ;
                
                PreparedStatement statement;
                

            try {
                    statement = cnx2.prepareStatement(req);
                   
                    statement.setInt(1,idu);
                    statement.setInt(2,idm);
             
                    statement.executeUpdate();
                    
                    System.out.println("reservation added");

                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    
            }
      
       
    }

    
    public void UpdateRes(Reservation t,int idu, int idm) {
                    String req = "UPDATE reservation SET (id_utilisateur, "
                        + "id_match,nom_utilisateur, prenom_utilisateur, time, date, location) "
                        + "SELECT u.Id_utilisateur, m.id_match, u.Nom, u.Prenom, m.time, m.date, m.location "
                        + "from utilisateurs u, matchs m where u.Id_utilisateur=? and m.id_match=?" ;
                    
            PreparedStatement statement;
        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1,idu);
            statement.setInt(2,idm);
            statement.setString(3, t.getNom_utilisateur());
            statement.setString(4, t.getPrenom_utilisateur());
            statement.setTime(5, (Time) t.getTime());
            statement.setDate(6, (Date) t.getDate());
            statement.setString(7,t.getLocation());

            statement.executeUpdate();

            System.out.println("Match updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    @Override
    public void Delete(Reservation t) {
        
            String req = "DELETE FROM matchs WHERE id_ticket=?";
            PreparedStatement statement;
        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1,t.getId_ticket());
            statement.executeUpdate();

            System.out.println("Reservation deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
    }

  
    public List<Reservation> RetrieveRes() {
            List<Reservation> ListReservation = new ArrayList<Reservation>();
            PreparedStatement statement;
            String req = "Select * From reservation Where Id_utilisateur =?";
            try {
               statement = cnx2.prepareStatement(req);

                ResultSet rst;
                rst = statement.executeQuery(req);
                     Reservation t = new Reservation();
                    statement.setInt(1,t.getId_utilisateur());
                
                while(rst.next())
                {
                    
                    t.setId_ticket(rst.getInt(1));
                    t.setId_match(rst.getInt(2));
                    t.setId_utilisateur(rst.getInt(3));
                    t.setNom_utilisateur(rst.getString("nom_utilisateur"));
                    t.setPrenom_utilisateur(rst.getString("prenom_utilisateur"));
                    t.setDate(rst.getDate("date"));
                    t.setTime(rst.getTime("time"));
                    t.setLocation(rst.getString("location"));
                    
                    ListReservation.add(t);
                    
                    
                }
                
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            
            return ListReservation;
    }


    @Override
    public List<Reservation> Retrieve() {
            List<Reservation> ListReservation = new ArrayList<Reservation>();
            PreparedStatement statement;
            String req = "Select * From reservation";
            try {
               statement = cnx2.prepareStatement(req);

                ResultSet rst;
                rst = statement.executeQuery(req);
                     Reservation t = new Reservation();

                while(rst.next())
                {
                    
                    t.setId_ticket(rst.getInt(1));
                    t.setId_match(rst.getInt(2));
                    t.setId_utilisateur(rst.getInt(3));
                    t.setNom_utilisateur(rst.getString("nom_utilisateur"));
                    t.setPrenom_utilisateur(rst.getString("prenom_utilisateur"));
                    t.setDate(rst.getDate("date"));
                    t.setTime(rst.getTime("time"));
                    t.setLocation(rst.getString("location"));
                    
                    ListReservation.add(t);
                    
                    
                }
                
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            
            return ListReservation;
           
    }


    @Override
    public void Create(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
