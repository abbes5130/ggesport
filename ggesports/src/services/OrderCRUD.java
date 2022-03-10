
package services;

import entities.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;


public class OrderCRUD implements IService<Order>{
    
    Connection cnx;
    
    public OrderCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void add(Order o) {
        try{
            String RQ = "INSERT INTO orders (id_user, order_date, order_time, state)"
            + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(RQ);
    
            pst.setInt(1, o.getIdUser());
            pst.setDate(2, o.getOrderDate());
            pst.setTimestamp(3, o.getOrderTime());
            pst.setBoolean(4, o.getState());

            
            pst.executeUpdate();
            System.out.println("Order added successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void delete(Order o) {
        try{
            String RQ="delete from order1 where id_user=?";
            PreparedStatement pst= cnx.prepareStatement(RQ);
            pst.setInt(1, o.getIdUser());
            
            pst.executeUpdate();
            System.out.println("order deleted successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void update(Order o) {
        try{
            String RQ = "update order1 set date=?, time=?, state=? where id_user=?";
            PreparedStatement pst = cnx.prepareStatement(RQ);
            pst.setDate(1, o.getOrderDate());
            pst.setTimestamp(2, o.getOrderTime());
            pst.setBoolean(3, o.getState());
            pst.setInt(4, o.getIdUser());
            
            pst.executeUpdate();
            System.out.println("order modified succefully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> Orders = new ArrayList<Order>();
        try{
            String RQ= "SELECT * FROM order1";
            Statement st= cnx.createStatement();
            ResultSet rs = st.executeQuery(RQ);
            Order o = new Order();

            while(rs.next()){
                o.setIdOrder(rs.getInt("id_order"));
                o.setIdUser(rs.getInt("id_user"));
                o.setOrderDate(rs.getDate("date"));
                o.setOrderTime(rs.getTimestamp("time"));
                o.setState(rs.getBoolean("state"));
            }
            System.out.println("All orders extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return Orders;
    }
    
}
    
    

