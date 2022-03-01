
package entities;

import java.sql.Date;
import java.sql.Timestamp;


public class Order {
    private int idOrder;
    private int idUser;
    private Date orderDate;
    private Timestamp orderTime;
    private Boolean state;

    public Order() {
    }
    
    public Order(int idUser, Boolean state) {
        this.idUser = idUser;
        this.state = state;
    }

    public Order(int idUser, Date orderDate, Timestamp orderTime, Boolean state) {
        this.idUser = idUser;
        this.orderDate = orderDate;
        this.orderTime= orderTime;
        this.state = state;
    }

    
    public Order(int idOrder, int idUser, Date orderDate, Timestamp orderTime, Boolean state) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.orderDate = orderDate;
        this.orderTime= orderTime;
        this.state = state;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" + "idOrder=" + idOrder + ", idUser=" + idUser + ", orderDate=" + orderDate + ", orderTime=" + orderTime + ", state=" + state + '}';
    }

    
    
}
