/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.actualité;
import entities.commentaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.connexion;
import java.sql.Connection;

/**
 *
 * @author USER
 */
public class actualiteCRUD {
    Connection  cnx2;
    public actualiteCRUD(){
        cnx2 = connexion.getIstance().getcnx();
    }
    public void ajouteractualite(){
        String requete ="INSERT INTO actualité (Titre,img_bg,img,description,date_creation)" + "VALUES ('the most','http','http','you will see a winner a great event that have many opportunite to teams to have 100 $','2023-08-11')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("actualité ajouté avec succés ");
        } catch (SQLException ex) {
     System.err.println(ex.getMessage());   }
    }
    public void ajouteractualite2(actualité a){
        String requete2="INSERT INTO actualité (Titre,img_bg,img,description,date_creation)" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requete2);
                        pst.setString(1, a.getTitre());
                        pst.setString(2, a.getImg_bg());
                        pst.setString(3, a.getImg());


            pst.setString(4, a.getDescription());

            pst.setDate(5, a.getDate_creation());
            pst.executeUpdate();
            System.out.println("votre actualité est ajouté");
        } catch (SQLException ex) {
 System.err.println(ex.getMessage());
        }
    }
     public void Updateactualite2(actualité a){
              String requete4 = "UPDATE actualité SET Titre = ?, img_bg =?,img =?, description=?, date_creation=?  WHERE id_actualite = ? ";
 try {
      PreparedStatement st = cnx2.prepareStatement(requete4);
 st.setInt(6, a.getId());
    st.setString(1,a.getTitre());
    st.setString(2, a.getImg_bg());
    st.setString(3, a.getImg());
        st.setString(4,a.getDescription());
            st.setDate(5, a.getDate_creation());

    st.executeUpdate();
    System.out.println("Operation done successfully");

    } catch ( SQLException ex ) {
        System.err.println(ex.getMessage()); 
    }
    }
        public void deleteactualite2(actualité a){
              String requete5 = "DELETE FROM actualité WHERE id_actualite = ? ";
 try {
      PreparedStatement st = cnx2.prepareStatement(requete5);
 st.setInt(1, a.getId());
    st.executeUpdate();
    System.out.println("Operation done successfully");

    } catch ( SQLException ex ) {
        System.err.println(ex.getMessage()); 
    }
    }
    public List<actualité> afficheractualite() {
        
        List<actualité> mylist =new ArrayList<>();
        try {
                    String requete3 = "SELECT * FROM actualité";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                actualité a = new actualité();
                a.setId(rs.getInt(1));
                a.setTitre(rs.getString("Titre"));
                a.setImg_bg(rs.getString("img_bg"));
                a.setImg(rs.getString("img"));
                a.setDescription(rs.getString("description"));
                a.setDate_creation(rs.getDate(6));
                mylist.add(a);

            }
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());

        }
        return mylist;
    }
}
