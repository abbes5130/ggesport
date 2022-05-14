/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.News;
import entities.Comments;
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
import utils.BDUTIL;
import utils.connectionfactory;

/**
 *
 * @author USER
 */
public class NewsCRUD {
   private Statement statement;
    Connection  cnx2;
     private Connection connection;
    public NewsCRUD(){
        cnx2 = connexion.getIstance().getcnx();
       
    }
    public void ajouteractualite(){
        String requete ="INSERT INTO News (title,bg_img,img,description,creation_date)" + "VALUES ('the most','http','http','you will see a winner a great event that have many opportunite to teams to have 100 $','2023-08-11')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("actualité ajouté avec succés ");
        } catch (SQLException ex) {
     System.err.println(ex.getMessage());   }
    }
    public void ajouteractualite2(News a){
        String requete2="INSERT INTO News (title,bg_img,img,description,creation_date)" + "VALUES(?,?,?,?,?)";
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
     public void Updateactualite2(News a){
              String requete4 = "UPDATE News SET title = ?, bg_img =?,img =?, description=?, creation_date=?  WHERE id = ? ";
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
        public void deleteactualite2(News a){
              String requete5 = "DELETE FROM News WHERE id = ? ";
 try {
      PreparedStatement st = cnx2.prepareStatement(requete5);
 st.setInt(1, a.getId());
    st.executeUpdate();
    System.out.println("Operation done successfully");

    } catch ( SQLException ex ) {
        System.err.println(ex.getMessage()); 
    }
    }
    public List<News> afficheractualite() {
        
        List<News> mylist =new ArrayList<>();
        try {
                    String requete3 = "SELECT * FROM News";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                News a = new News();
                a.setId(rs.getInt(1));
                a.setTitre(rs.getString("title"));
                a.setImg_bg(rs.getString("bg_img"));
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
 public News getactualité(int id) throws SQLException {

        String query = "SELECT * FROM News WHERE id =  " + id;
                          News actualité = null;
                                            ResultSet rs = null;


        try {
connection = connectionfactory.getConnection();
            statement = connection.createStatement();           
rs = statement.executeQuery(query);           
if (rs.next()) {
                  actualité  = new News();

               actualité.setId(rs.getInt(1));
                actualité.setTitre(rs.getString("title"));
                actualité.setImg_bg(rs.getString("bg_img"));
                actualité.setImg(rs.getString("img"));
                actualité.setDescription(rs.getString("description"));
                actualité.setDate_creation(rs.getDate("creation_date"));
            }
         } finally {
            BDUTIL.close(rs);
            BDUTIL.close(statement);
            BDUTIL.close(connection);
        }
        return actualité;
    }
 public List<News> getactualités() throws SQLException {
        String query = "SELECT * FROM News";
        List<News> list = new ArrayList<News>();
        News actualité = null;
        ResultSet rs = null;
        try {
            connection = connectionfactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                actualité = new News();
                /*Retrieve one employee details 
                and store it in employee object*/
                actualité.setTitre(rs.getString("title"));
                actualité.setImg_bg(rs.getString("bg_img"));
                actualité.setImg(rs.getString("img"));
                actualité.setDescription(rs.getString("description"));
                actualité.setDate_creation(rs.getDate("creation_date"));
 
                //add each employee to the list
                list.add(actualité);
            }
        } finally {
            BDUTIL.close(rs);
            BDUTIL.close(statement);
            BDUTIL.close(connection);
        }
        return list;
    }
public int getRownumber() {
        int numberRow =0;
        try {
                    String requete3 = "SELECT count(*) FROM News";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                 numberRow = rs.getInt("count(*)");

            }
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());

        }
        return numberRow;
    }
    public void ajout(News p) {
    }
    
    
    public List<News> afficheractualiteTitre() {
        
        List<News> mylist =new ArrayList<>();
        try {
                    String requete3 = "SELECT title FROM News";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                News a = new News();
                //a.setId(rs.getInt(1));
                a.setTitre(rs.getString("title"));
                //a.setImg_bg(rs.getString("img_bg"));
                //a.setImg(rs.getString("img"));
                //a.setDescription(rs.getString("description"));
                //a.setDate_creation(rs.getDate(6));
                mylist.add(a);

            }
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());

        }
        return mylist;
    }
    public int nombreCommentaires(News a){
        int nbrCommentaires =0;
        try {
            String req="select count(*) from comments where id ="+a.getId();
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.next();
            nbrCommentaires = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbrCommentaires;
    }
      
    public void ajouter(News a) {
         try {
          
           String req="insert into News(description) values(?)";
                PreparedStatement smt = cnx2.prepareStatement(req);
                smt.setString(1, a.getDescription());
                smt.executeUpdate();
                System.out.println("Ajout de actualité avec succées");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }
    public void modifier(News a) {
    try {
       String req="update News set description=? where id=?";
                PreparedStatement smt = cnx2.prepareStatement(req);
                
                smt.setString(1, a.getDescription());
                smt.setInt(2, a.getId());
                smt.executeUpdate();
                System.out.println("Modification d' actualité avec succées");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
     public List<News> find() {
    ArrayList l=new ArrayList(); 
        
        try {
       String req="select * from News";
                PreparedStatement smt = cnx2.prepareStatement(req);
                
                ResultSet rs= smt.executeQuery(req);
                while(rs.next()){
                   News a =new News(rs.getInt("id"),rs.getString("title"),rs.getString("bg_img"),rs.getString("img"),rs.getString("description"),rs.getDate("creation_date"));
                   l.add(a);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    }
}




