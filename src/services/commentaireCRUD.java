/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.actualité;
import entites.Commentaire;
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
        String requete ="INSERT INTO commentaire (id_actualite, texte, date_commentaire)" + "VALUES (1,'you will see a winner a great event that have many opportunite to teams to have 100 $','2023-08-11')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("commentaire ajouté avec succés ");
        } catch (SQLException ex) {
     System.err.println(ex.getMessage());   }
    }
    public void ajoutercommmentaire2(Commentaire a){
        String requete2="INSERT INTO commentaire (texte, id_actualite,date_commentaire)" + "VALUES(?,?,?)";
        try {
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setInt(2, a.getId_actualite());
                        pst.setString(1, a.gettexte());
                        pst.setDate(3, a.getDate_commentaire());

            pst.executeUpdate();
            System.out.println("votre commentaire est ajouté");
        } catch (SQLException ex) {
 System.err.println(ex.getMessage());
        }
    }
    public void Updatecommmentaire2(Commentaire a){
              String requete4 = "UPDATE commentaire SET  texte = ?, id_actualite= ? WHERE id_commentaire = ? ";
 try {
      PreparedStatement st = cnx2.prepareStatement(requete4);
 st.setInt(3, a.getId_commentaire());
    st.setInt(2,a.getId_actualite());
    st.setString(1, a.getTexte());
    st.executeUpdate();
    System.out.println("Operation done successfully");

    } catch ( SQLException ex ) {
        System.err.println(ex.getMessage()); 
    }
    }

     public void deletecommmentaire2(Commentaire a){
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
    public List<Commentaire> affichercommentaire() {
        List<Commentaire> mylist =new ArrayList<>();
        try {
                    String requete3 = "SELECT * FROM commentaire";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Commentaire a = new Commentaire();
                a.setId_commentaire(rs.getInt(1));
                a.setId_actualite(rs.getInt(2));
                a.settexte(rs.getString("texte"));
                a.setDate_commentaire(rs.getDate(5));

                mylist.add(a);

            }
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());

        }
        return mylist;
    }
     public List<Commentaire> find(int id_act) {
    ArrayList l=new ArrayList(); 
        
        try {
       String req="SELECT * FROM commentaire WHERE id_actualite ="+id_act;
                Statement smt = cnx2.createStatement();
              
                Commentaire c;
                ResultSet rs= smt.executeQuery(req);
                while(rs.next()){
                   c=new Commentaire(rs.getInt("id_commentaire"),rs.getInt("id_actualite"),rs.getString("texte"),rs.getDate("date_commentaire"));
                   l.add(c);
                   //rs.getInt("id_commentaire"),
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    }
 public void ajouter(Commentaire c) {
         try {
          
           String req="insert into commentaire(texte,id_actualite,date_commentaire) values(?,?,?)";
                PreparedStatement smt = cnx2.prepareStatement(req);
                smt.setString(1, c.getTexte());
                smt.setInt(2, c.getId_actualite());
                                        smt.setDate(3, c.getDate_commentaire());

                
                smt.executeUpdate();
                System.out.println("Ajout de commentaire avec succées");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }
}
