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
import utils.MyConnexion;

/**
 *
 * @author ridha
 */
public class UtilisateursServices implements UserServices<Users>{
    Connection cnx;
    public UtilisateursServices() {
        cnx = MyConnexion.getInstance().getConncetion();
    }

    @Override
    public void ajouter(Users t) {
        
            try {
          
           String query="INSERT INTO users(firstname,lastname,email,password,phone_number,id_role) values(?,?,?,?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getFirstname());
                smt.setString(2, t.getLastname());
                smt.setString(3, t.getEmail());
                smt.setString(4, t.getPassword());
                smt.setInt(5, t.getPhone_number());
                smt.setInt(6, t.getId_role());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
        
    }
    @Override
    public void Sign_in(Users t) {
        
            try {
          
           String query="INSERT INTO users(firstname,lastname,email,password,phone_number,id_role) values(?,?,?,?,?,3)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getFirstname());
                smt.setString(2, t.getLastname());
                smt.setString(3, t.getEmail());
                smt.setString(4, t.getPassword());
                smt.setInt(5, t.getPhone_number());
               
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
        
    }

    @Override
    public void modifier(Users t) {
         try {
       String query2="update users set firstname=?, lastname=?, email=?, password=?,phone_number=? where id_user=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                
                smt.setString(1, t.getFirstname());
                smt.setString(2, t.getLastname());
                smt.setString(3, t.getEmail());
                smt.setString(4, t.getPassword());
                smt.setInt(5, t.getPhone_number());
                smt.setInt(6, t.getId_user());
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public void supprimer(Users t) {
        
     try {
       String query2="delete from users where id_user=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_user());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public List<Users> find() {
    
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from users join role on users.id_role=role.id_role";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Users p;
                Role r;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Users(rs.getInt("id_user"),rs.getInt("phone_number"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"));
                   r=new Role(rs.getString("rolename"));
                   l.add(p);
                   l.add(r);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    
}}
