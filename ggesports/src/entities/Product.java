
package entities;


public class Product {
    private int id, discount, categorie, quantite_stock;
    private float price;
    private String name, color, brand, description;
    private Boolean available;

    public Product(int id, String name, float price,  String description, String color, String brand, int discount,  Boolean available, int categorie, int quantite_stock) {
        this.id = id;
        this.discount = discount;
        this.categorie = categorie;
        this.quantite_stock = quantite_stock;
        this.price = price;
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.description = description;
        this.available = available;
    }

    
    
    public Product(String name, float price,  String description, String color, String brand, int discount,  Boolean available, int categorie, int quantite_stock) {
        this.discount = discount;
        this.categorie= categorie;
        this.quantite_stock= quantite_stock;
        this.price = price;
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.description = description;
        this.available = available;
    }

    public Product() {}     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getQuantite_stock() {
        return quantite_stock;
    }

    public void setQuantite_stock(int quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", discount=" + discount + ", price=" + price + ", name=" + name + ", color=" + color + ", brand=" + brand + ", description=" + description + ", available=" + available + '}';
    }


    
    
    
}
