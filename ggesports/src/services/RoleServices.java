/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Role;
import entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnexion;

/**
 *
 * @author ridha
 */
public class RoleServices implements RServices<Role>{
    Connection cnx;
    public RoleServices() {
        cnx = MyConnexion.getInstance().getConncetion();
    }

    @Override
    public void ajouterRole(Role t) {
        try {
          
           String query="INSERT INTO role(rolename) values(?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getRolename());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }

    @Override
    public void modifierRole(Role t) {
        try {
       String query2="update role set rolename=? where id_role=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                
                smt.setString(1, t.getRolename());
                smt.setInt(2, t.getId_role());
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public void supprimerRole(Role t) {
        try {
       String query2="delete from role where id_role=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_role());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<Role> findRole() {
         ObservableList<Role> List = FXCollections.observableArrayList();

        
        try {
       String query2="select * from role";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Role p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Role(rs.getInt("id_role"), rs.getString("rolename"));
                   List.add(p);
                }
                System.out.println(List);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return List;
    }
    
}
