
package services;

import entities.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;


public class CategoryCRUD implements IService<Category>{
    
    Connection cnx;

    public CategoryCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void add(Category C) {
        try{
            String RQ = "INSERT INTO Category (category_name)"
            + "VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(RQ);
            pst.setString(1, C.getCategory_name());            
            
            pst.executeUpdate();
            System.out.println("category added succefully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void delete(Category C) {
        try{
            String RQ="delete from category where id_category=?";
            PreparedStatement pst= cnx.prepareStatement(RQ);
            pst.setInt(1, C.getId_category());
            
            pst.executeUpdate();
            System.out.println("category deleted successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void update(Category C) {
        try{
            String RQ = "update category set category_name=?";
            PreparedStatement pst = cnx.prepareStatement(RQ);
            pst.setString(1, C.getCategory_name());
            
            pst.executeUpdate();
            System.out.println("category updated succefully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public List<Category> getAll() {
        List<Category> Categories = new ArrayList<Category>();
        try{
            String RQ= "SELECT * FROM category";
            Statement st= cnx.createStatement();
            ResultSet rs = st.executeQuery(RQ);
            
            Category c = new Category();

            while(rs.next()){
                c.setId_category(rs.getInt("id_category"));
                c.setCategory_name(rs.getString("category_name"));
            }
            System.out.println("All categories extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return Categories;
    }
    

}
    
