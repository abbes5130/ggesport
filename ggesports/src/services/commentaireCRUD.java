/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.actualité;
import entities.commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.connexion;

/**
 *
 * @author USER
 */
public class commentaireCRUD {
      Connection  cnx2;
    public commentaireCRUD(){
        cnx2 = connexion.getIstance().getcnx();
    }
    public void ajoutercommentaire(){
        String requete ="INSERT INTO commentaire (id_actualite, id_utilisateur, texte, date_commentaire)" + "VALUES (1,1,'you will see a winner a great event that have many opportunite to teams to have 100 $','2023-08-11')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("commentaire ajouté avec succés ");
        } catch (SQLException ex) {
     System.err.println(ex.getMessage());   }
    }
    public void ajoutercommmentaire2(commentaire a){
        String requete2="INSERT INTO commentaire (id_actualite, id_utilisateur, texte, date_commentaire)" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setInt(1, a.getId_actualite());
            pst.setInt(2, a.getId_utilisateur());
                        pst.setString(3, a.gettexte());
                        pst.setDate(4, a.getDate_commentaire());

            pst.executeUpdate();
            System.out.println("votre commentaire est ajouté");
        } catch (SQLException ex) {
 System.err.println(ex.getMessage());
        }
    }
    public void Updatecommmentaire2(commentaire a){
              String requete4 = "UPDATE commentaire SET id_actualite = ?, id_utilisateur = ? WHERE id_commentaire = ? ";
 try {
      PreparedStatement st = cnx2.prepareStatement(requete4);
 st.setInt(3, a.getId_commentaire());
    st.setInt(1,a.getId_actualite());
    st.setInt(2, a.getId_utilisateur());
    st.executeUpdate();
    System.out.println("Operation done successfully");

    } catch ( SQLException ex ) {
        System.err.println(ex.getMessage()); 
    }
    }

     public void deletecommmentaire2(commentaire a){
              String requete5 = "DELETE FROM commentaire WHERE id_commentaire = ? ";
 try {
      PreparedStatement st = cnx2.prepareStatement(requete5);
 st.setInt(1, a.getId_commentaire());
    st.executeUpdate();
    System.out.println("Operation done successfully");

    } catch ( SQLException ex ) {
        System.err.println(ex.getMessage()); 
    }
    }
    public List<commentaire> affichercommentaire() {
        List<commentaire> mylist =new ArrayList<>();
        try {
                    String requete3 = "SELECT * FROM commentaire";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                commentaire a = new commentaire();
                a.setId_commentaire(rs.getInt(1));
                a.setId_actualite(rs.getInt(2));
                a.setId_utilisateur(rs.getInt(3));
                a.settexte(rs.getString("texte"));
                a.setDate_commentaire(rs.getDate(5));

                mylist.add(a);

            }
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());

        }
        return mylist;
    }
}
