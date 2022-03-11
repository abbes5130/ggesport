
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
    CategoryCRUD ccrud= new CategoryCRUD();
    
    public ProductCRUD(){
        cnx = MyConnection.getInstance().getCnx();
    }
    


    @Override
    public void add(Product p) {
        int idCategory=ccrud.getIdCategoryByName(p.getCategorie());
        try{
            String requete2 = "INSERT INTO product (product_name, product_price, description, color, mark, discount, disponibility, category, stock_quantity, image)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, p.getName());
            pst.setFloat(2, p.getPrice());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getColor());
            pst.setString(5, p.getBrand());
            pst.setInt(6, p.getDiscount());
            pst.setBoolean(7, p.getAvailable());
            pst.setInt(8, idCategory);
            pst.setInt(9, p.getQuantite_stock());
            pst.setString(10, p.getImage());
            
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
    
    public void deleteProductById(int i) {
        try{
            String requete4="delete from product where id_product=?";
            PreparedStatement pst= cnx.prepareStatement(requete4);
            pst.setInt(1, i);
            
            pst.executeUpdate();
            System.out.println("Product deleted successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void update(Product p) {
        int idCategory=ccrud.getIdCategoryByName(p.getCategorie());
        try{
            String requete3 = "update product set product_name=?, product_price=?, description=?, color=?, mark=?, discount=?, disponibility=?, category=?, stock_quantity=?,image=?, where id_product=?";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, p.getName());
            pst.setFloat(2, p.getPrice());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getColor());
            pst.setString(5, p.getBrand());
            pst.setInt(6, p.getDiscount());
            pst.setBoolean(7, p.getAvailable());
            pst.setInt(8, idCategory);
            pst.setInt(9, p.getQuantite_stock());
            pst.setString(10, p.getImage());
            pst.setInt(11, p.getId());
            
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
            
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("id_product"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getFloat("product_price"));
                p.setAvailable(rs.getBoolean("disponibility"));
                p.setBrand(rs.getString("mark"));
                p.setImage(rs.getString("image"));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setDiscount(rs.getInt("discount"));
                p.setCategorie(rs.getString("category"));
                p.setQuantite_stock(rs.getInt("stock_quantity"));
                AllProducts.add(p);
            }
            System.out.println("All Products extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return AllProducts;
    }

    public Product getProductById(int id){
        Product p = new Product();
        try{
            String rq= "SELECT * FROM product where id_product=?";
            PreparedStatement pst = cnx.prepareStatement(rq);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int categoryId=rs.getInt("category");
                String categoryName=ccrud.getCategoryNameById(categoryId);
                p.setId(rs.getInt("id_product"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getFloat("product_price"));
                p.setAvailable(rs.getBoolean("disponibility"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("mark"));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setDiscount(rs.getInt("discount"));
                p.setCategorie(categoryName);
                p.setQuantite_stock(rs.getInt("stock_quantity"));
            }
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return p;
    }
    
    public List<Product> searchProducts(String s){
        List<Product> AllProducts = new ArrayList<Product>();
        try{
            String RQ= "SELECT * FROM product p join category c on p.category=c.id_category where p.product_name LIKE CONCAT( '%',?,'%') or p.description LIKE CONCAT( '%',?,'%') or p.mark LIKE CONCAT( '%',?,'%') or c.category_name LIKE CONCAT( '%',?,'%')";
            PreparedStatement pst = cnx.prepareStatement(RQ);
            pst.setString(1, s);
            pst.setString(2, s);
            pst.setString(3, s);
            pst.setString(4, s);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int categoryId=rs.getInt("category");
                String categoryName=ccrud.getCategoryNameById(categoryId);
                Product p = new Product();
                p.setId(rs.getInt("id_product"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getFloat("product_price"));
                p.setAvailable(rs.getBoolean("disponibility"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("mark"));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setDiscount(rs.getInt("discount"));
                p.setCategorie(categoryName);
                p.setQuantite_stock(rs.getInt("stock_quantity"));
                AllProducts.add(p);
            }
            System.out.println("All Products extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return AllProducts;
    }
    
    public List<Product> getProductsByCategory(String s){
        List<Product> AllProducts = new ArrayList<Product>();
        try{
            String RQ= "SELECT * FROM product p join category c on p.category=c.id_category where c.category_name=?";
            PreparedStatement pst = cnx.prepareStatement(RQ);
            pst.setString(1, s);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Product p = new Product();
                int categoryId=rs.getInt("category");
                String categoryName=ccrud.getCategoryNameById(categoryId);
                p.setId(rs.getInt("id_product"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getFloat("product_price"));
                p.setAvailable(rs.getBoolean("disponibility"));
                p.setBrand(rs.getString("mark"));
                p.setImage(rs.getString("image"));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setDiscount(rs.getInt("discount"));
                p.setCategorie(categoryName);
                p.setQuantite_stock(rs.getInt("stock_quantity"));
                AllProducts.add(p);
            }
            System.out.println("All Products extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return AllProducts;
    }
    
    public List<Product> getProductsByPrice(float min, float max){
        List<Product> AllProducts = new ArrayList<Product>();
        try{
            String RQ= "SELECT * FROM product WHERE product_price >= ? and product_price <= ?";
            PreparedStatement pst = cnx.prepareStatement(RQ);
            pst.setFloat(1, min);
            pst.setFloat(2, max);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Product p = new Product();
                int categoryId=rs.getInt("category");
                String categoryName=ccrud.getCategoryNameById(categoryId);
                p.setId(rs.getInt("id_product"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getFloat("product_price"));
                p.setAvailable(rs.getBoolean("disponibility"));
                p.setBrand(rs.getString("mark"));
                p.setImage(rs.getString("image"));
                p.setColor(rs.getString("color"));
                p.setDescription(rs.getString("description"));
                p.setDiscount(rs.getInt("discount"));
                p.setCategorie(categoryName);
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
