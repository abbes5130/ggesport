
package services;

import entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

public class ProductCRUD implements IService<Product>{
    
    Connection cnx;
    
    public ProductCRUD(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void addProduct(){
        try{
            String requete = "INSERT INTO product (product_name, product_price, description, color, mark, discount, disponibility, category, stock_quantity)"
            + "VALUES ('capuche',69.990,'green capuche ','green', 'csgo', 10, true, 1, 5)";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajouté avec succès");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    

    @Override
    public void add(Product p) {
        try{
            String requete2 = "INSERT INTO product (product_name, product_price, description, color, mark, discount, disponibility, category, stock_quantity)"
            + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, p.getName());
            pst.setFloat(2, p.getPrice());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getColor());
            pst.setString(5, p.getBrand());
            pst.setInt(6, p.getDiscount());
            pst.setBoolean(7, p.getAvailable());
            pst.setInt(8, p.getCategorie());
            pst.setInt(9, p.getQuantite_stock());
            
            pst.executeUpdate();
            System.out.println("Produit ajouté avec succès");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void delete(Product p) {
        try{
            String requete4="delete from product where id_product=?";
            PreparedStatement pst= cnx.prepareStatement(requete4);
            pst.setInt(1, p.getId());
            
            pst.executeUpdate();
            System.out.println("Product deleted successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void update(Product p) {
        try{
            String requete3 = "update product set product_name=?, product_price=?, description=?, color=?, mark=?, discount=?, disponibility=?, category=?, stock_quantity=? where id_product=?";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, p.getName());
            pst.setFloat(2, p.getPrice());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getColor());
            pst.setString(5, p.getBrand());
            pst.setInt(6, p.getDiscount());
            pst.setBoolean(7, p.getAvailable());
            pst.setInt(8, p.getCategorie());
            pst.setInt(9, p.getQuantite_stock());
            pst.setInt(10, p.getId());
            
            pst.executeUpdate();
            System.out.println("Produit modifié avec succès");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> AllProducts = new ArrayList<Product>();
        try{
            String requete5= "SELECT * FROM product";
            Statement st= cnx.createStatement();
            ResultSet rs = st.executeQuery(requete5);
            Product p = new Product();

            while(rs.next()){
                p.setId(rs.getInt("id_product"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getFloat("product_price"));
                p.setAvailable(rs.getBoolean("disponibility"));
                p.setBrand(rs.getString("mark"));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setDiscount(rs.getInt("discount"));
                p.setCategorie(rs.getInt("category"));
                p.setQuantite_stock(rs.getInt("stock_quantity"));
                AllProducts.add(p);
            }
            System.out.println("All Products extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return AllProducts;
    }



}
