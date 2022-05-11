
package com.mycompany.entites;

public class Product {

    private int id, discount, quantite_stock, idCategory;
    private float price;
    private String name, color, brand, description, image;
    private Boolean available;

    public Product() {
    }

    public Product( String name, String description,float price,int idCategory) {
        this.idCategory = idCategory;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    
    public Product(int id, int discount, int quantite_stock, int idCategory, float price, String name, String color, String brand, String description, String image, Boolean available) {
        this.id = id;
        this.discount = discount;
        this.quantite_stock = quantite_stock;
        this.idCategory = idCategory;
        this.price = price;
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.available = available;
    }

    public Product(int quantite_stock, int idCategory, float price, String name, String color, String brand, String description) {

        this.quantite_stock = quantite_stock;
        this.idCategory = idCategory;
        this.price = price;
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.description = description;
    }
    

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantite_stock() {
        return quantite_stock;
    }

    public void setQuantite_stock(int quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", discount=" + discount + ", quantite_stock=" + quantite_stock + ", idCategory=" + idCategory + ", price=" + price + ", color=" + color + ", brand=" + brand + ", description=" + description + ", image=" + image + ", available=" + available + '}';
    }

    
}
