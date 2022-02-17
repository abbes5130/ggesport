/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Role;
import entities.Utilisateurs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
          
           String query="INSERT INTO role(Nom_role) values(?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getNom_role());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }

    @Override
    public void modifierRole(Role t) {
        try {
       String query2="update role set Nom_role=? where Id_role=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setString(1, t.getNom_role());
                
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public void supprimerRole(Role t) {
        try {
       String query2="delete from role where Id_role=?";
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
        ArrayList l2=new ArrayList(); 
        
        try {
       String query2="select * from utilisateurs";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Role p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Role(rs.getInt("Id_role"), rs.getString("Nom_role"));
                   l2.add(p);
                }
                System.out.println(l2);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l2;
    }
    
}
