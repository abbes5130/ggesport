
package entities;


public class ProductOrder {
    private int idProductOrder;
    private int idOrder;
    private int idProduct;
    private int quantity;

    public ProductOrder() {
    }

    public ProductOrder(int idProductOrder, int idOrder, int idProduct, int quantity) {
        this.idProductOrder = idProductOrder;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public ProductOrder(int idOrder, int idProduct, int quantity) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }
    
    

    public int getIdProductOrder() {
        return idProductOrder;
    }

    public void setIdProductOrder(int idProductOrder) {
        this.idProductOrder = idProductOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductOrder{" + "idProductOrder=" + idProductOrder + ", idOrder=" + idOrder + ", idProduct=" + idProduct + ", quantity=" + quantity + '}';
    }
    
    
}
