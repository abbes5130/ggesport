
package entities;


public class Category {
    private int id_category;
    private String category_name;

    public Category() {
    }

    public Category(String category_name) {
        this.category_name = category_name;
    }
       

    public Category(int id_category, String category_name) {
        this.id_category = id_category;
        this.category_name = category_name;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Category{" + "id_category=" + id_category + ", category_name=" + category_name + '}';
    }
    
    
}
