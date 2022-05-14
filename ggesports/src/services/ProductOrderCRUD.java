/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ProductOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author Firas
 */
public class ProductOrderCRUD implements IService<ProductOrder>{

    Connection cnx;

    public ProductOrderCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void add(ProductOrder PO) {
        try{
            String requete2 = "INSERT INTO product_order (id_order, id_product, quantity)"
            + "VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, PO.getIdOrder());
            pst.setInt(2, PO.getIdProduct());
            pst.setInt(3, PO.getQuantity());

            pst.executeUpdate();
            System.out.println("ProductOrder added successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }    
    }

    @Override
    public void delete(ProductOrder PO) {
        try{
            String requete4="delete from product_order where id_product_order=?";
            PreparedStatement pst= cnx.prepareStatement(requete4);
            pst.setInt(1, PO.getIdProductOrder());
            
            pst.executeUpdate();
            System.out.println("Product deleted successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void update(ProductOrder PO) {
        try{
            String requete3 = "update product_order set id_order=?, id_product=?, quantity=?";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setInt(1, PO.getIdOrder());
            pst.setInt(2, PO.getIdProduct());
            pst.setInt(3, PO.getQuantity());

            pst.executeUpdate();
            System.out.println("ProductOrder modified successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public List<ProductOrder> getAll() {
        List<ProductOrder> ProductOrders = new ArrayList<ProductOrder>();
        try{
            String requete5= "SELECT * FROM product_order";
            Statement st= cnx.createStatement();
            ResultSet rs = st.executeQuery(requete5);
            ProductOrder po = new ProductOrder();

            while(rs.next()){
                po.setIdOrder(rs.getInt("id_order"));
                po.setIdProduct(rs.getInt("id_product"));
                po.setQuantity(rs.getInt("quantity"));
            }
            System.out.println("All productOrders extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ProductOrders;
    }

    @Override
    public void create(ProductOrder t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> Retrieve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Create(ProductOrder t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(ProductOrder t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(ProductOrder t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
