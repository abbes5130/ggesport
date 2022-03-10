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
public class ReservationService implements IService<Reservation> {

    Connection cnx2;

    public ReservationService() {
        cnx2 = MyDB.getInstance().getCnx();
    }

    public void CreateRes(int user_id, int match_id) {
        String req = "insert into reservation (id_user, "
                + "id_match) "
                + "SELECT u.id_user, m.id_match"
                + "from users u, matchs m where u.id_user=? and m.id_match=?";

        PreparedStatement statement;

        try {
            statement = cnx2.prepareStatement(req);

            statement.setInt(1, user_id);
            statement.setInt(2, match_id);

            statement.executeUpdate();

            System.out.println("reservation added");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    /*public void UpdateRes(Reservation reservation, int user_id, int match_id) {

        String req = "UPDATE reservation r JOIN matches m JOIN users u "
                + "SET r.id_match=m.id_match, r.id_user=u.id_user, r.firstname=u.firstname, "
                + "r.lastname=u.lastname, r.date=m.date, r.time=m.time, r.location=m.location "
                + "WHERE r.id_ticket =? AND m.id_match=? AND u.id_user =?";
        

        PreparedStatement statement;
        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1, reservation.getId_ticket());
            statement.setInt(2, match_id);
            statement.setInt(3, user_id);
            System.out.println(statement);
            statement.executeUpdate();

            System.out.println("Match updated");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }*/

    @Override
    public void Delete(Reservation t) {

        String req = "DELETE FROM matches WHERE id_ticket=?";
        PreparedStatement statement;
        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1, t.getId_ticket());
            statement.executeUpdate();

            System.out.println("Reservation deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Reservation> RetrieveRes() {
        List<Reservation> ListReservation = new ArrayList<Reservation>();
        PreparedStatement statement;
        String req = "Select * From reservation Where id_user =?";
        try {
            statement = cnx2.prepareStatement(req);

            ResultSet rst;
            rst = statement.executeQuery(req);
            Reservation t = new Reservation();
            statement.setInt(1, t.getId_user());

            while (rst.next()) {

                t.setId_ticket(rst.getInt(1));
                t.setId_match(rst.getInt(2));
                t.setId_user(rst.getInt(3));


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

            while (rst.next()) {

                t.setId_ticket(rst.getInt(1));
                t.setId_match(rst.getInt(2));
                t.setId_user(rst.getInt(3));


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

    @Override
    public void create(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
