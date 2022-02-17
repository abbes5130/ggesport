/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

    
    public void CreateRes(Reservation t, int idm, int idu) {
                        String req = "insert into reservation (id_utilisateur, "
                        + "id_match,nom_utilisateur, prenom_utilisateur, time, date, location) "
                        + "SELECT u.Id_utilisateur, m.id_match, u.Nom, u.Prenom, m.time, m.date, m.location "
                        + "from utilisateurs u, matchs m where u.Id_utilisateur=4 and m.id_match=?"  ;
                
                PreparedStatement statement;
                

            try {
                    statement = cnx2.prepareStatement(req);
                   
                    statement.setInt(1,t.getId_match());
             
                    statement.executeUpdate();
                    
                    System.out.println("reservation added");

                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    
            }
      
       
    }

    @Override
    public void Update(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> Retrieve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Create(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
}
