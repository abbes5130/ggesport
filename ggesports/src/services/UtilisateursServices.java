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
public class UtilisateursServices implements UserServices<Utilisateurs>{
    Connection cnx;
    public UtilisateursServices() {
        cnx = MyConnexion.getInstance().getConncetion();
    }

    @Override
    public void ajouter(Utilisateurs t) {
        
            try {
          
           String query="INSERT INTO utilisateurs(Nom,Prenom,Email,Password,Num_tel,Id_role) values(?,?,?,?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getNom());
                smt.setString(2, t.getPrenom());
                smt.setString(3, t.getEmail());
                smt.setString(4, t.getPassword());
                smt.setInt(5, t.getNum_tel());
                smt.setInt(6, t.getId_role());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
        
    }

    @Override
    public void modifier(Utilisateurs t) {
         try {
       String query2="update utilisateurs set Nom=?, Prenom=?, Email=?, Password=?,Num_tel=? where Id_utilisateur=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setString(1, t.getNom());
                smt.setString(2, t.getPrenom());
                smt.setString(3, t.getEmail());
                smt.setString(4, t.getPassword());
                smt.setInt(5, t.getNum_tel());
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public void supprimer(Utilisateurs t) {
        
     try {
       String query2="delete from utilisateurs where Id_utilisateur=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId_utilisateurs());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public List<Utilisateurs> find() {
    
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from utilisateurs join role on utilisateurs.Id_role=role.Id_role";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Utilisateurs p;
                Role r;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Utilisateurs(rs.getInt("Id_utilisateur"),rs.getInt("Id_role"), rs.getInt("Num_tel"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Email"),rs.getString("Password"));
                   r=new Role(rs.getString("nom_role"));
                   l.add(p);
                   l.add(r);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    
}}
