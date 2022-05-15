/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Product;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import services.ProductCRUD;


public class UpdateProductController implements Initializable {
    int i;
    @FXML
    private Button updateBtn;
    @FXML
    private Label productAddImg;
    @FXML
    private ComboBox<String> categoriesCmbx;
    private TextField productDiscountLabel;
    
    
    final FileChooser fc = new FileChooser();
    private String[] Categories= {"Tshirts","Sweatshirts","Pants","Accessories","Hardwares"};
    ProductCRUD pcrud =new ProductCRUD();
    
    
    @FXML
    private TextField productPriceField;
    @FXML
    private TextArea productDescriptionField;
    @FXML
    private TextField productBrandField;
    @FXML
    private TextField productColorField;
    @FXML
    private TextField productQuantityField;
    @FXML
    private TextField productDiscountField;
    @FXML
    private TextField productNameField;
    @FXML
    private Label U_filePath;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label brandErrorLabel;
    @FXML
    private Label priceErrorLabel;
    @FXML
    private Label colorErrorLabel;
    @FXML
    private Label quantityErrorLabel;
    @FXML
    private Label discountErrorLabel;
    @FXML
    private Label AlertLabel;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriesCmbx.getItems().addAll(Categories);
        categoriesCmbx.setOnAction(this::getCategory);
    }    
    
    public void getCategory(ActionEvent event){
        String Category = categoriesCmbx.getValue();
        System.out.println(Category);
    }

    public void setValues(int productId){
        Product p=pcrud.getProductById(productId);
        
        productAddImg.setText(p.getImage());
        productNameField.setText(p.getName());
        productDiscountField.setText(String.valueOf(p.getDiscount()));
        productQuantityField.setText(String.valueOf(p.getQuantite_stock()));
        productColorField.setText(p.getColor());
        productBrandField.setText(p.getBrand());
        productDescriptionField.setText(p.getDescription());
        productPriceField.setText(Float.toString(p.getPrice()));
        i = productId;
    }
    @FXML
    private void verifNameOnKeyReleased(KeyEvent event) {
        if(!Pattern.matches("^[a-zA-Z]*$", productNameField.getText())){
            nameErrorLabel.setText("the product name must contain only letters");
            productNameField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else{
            nameErrorLabel.setText("");
            productNameField.setStyle("-fx-border-color:#ffffff;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
    }

    @FXML
    private void verifPriceOnKeyReleased(KeyEvent event) {
        if(!Pattern.matches("^[0-9]*$", productPriceField.getText())){
            priceErrorLabel.setText("the product price must contain only digits");
            productPriceField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else{
            priceErrorLabel.setText("");
            productPriceField.setStyle("-fx-border-color:#ffffff;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
    }

    @FXML
    private void verifBrandOnKeyReleased(KeyEvent event) {
        if(!Pattern.matches("^[a-zA-Z]*$", productBrandField.getText())){
            brandErrorLabel.setText("the product brand must contain only letters");
            productBrandField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else{
            brandErrorLabel.setText("");
            productBrandField.setStyle("-fx-border-color:#ffffff;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
    }

    @FXML
    private void verifQuantityOnKeyReleased(KeyEvent event) {
        if(!Pattern.matches("^[0-9]*$", productQuantityField.getText())){
            quantityErrorLabel.setText("the product quantity must contain only digits");
            productQuantityField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else{
            quantityErrorLabel.setText("");
            productQuantityField.setStyle("-fx-border-color:#ffffff;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
    }

    @FXML
    private void verifDiscountOnKeyReleased(KeyEvent event) {
        if(productNameField.getText().length()>0&&productBrandField.getText().length()>0&&productPriceField.getText().length()>0&&productQuantityField.getText().length()>0){
            updateBtn.setDisable(false);
        }
        if(!Pattern.matches("^[0-9]*$", productDiscountField.getText())){
            discountErrorLabel.setText("the product discount must contain only digits");
            productDiscountField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else if(!Pattern.matches("^[0-9]{1,2}$", productDiscountField.getText())){
            discountErrorLabel.setText("the product discount must not exceed 2 digits");
            productDiscountField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else{
            discountErrorLabel.setText("");
            productDiscountField.setStyle("-fx-border-color:#ffffff;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
    }

    private void addProductImg(MouseEvent event) {
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.png","*.jpg"));
        File file = fc.showOpenDialog(null);
        if(file!=null){
            productAddImg.setText(file.toURI().toString());
            File sourcefile= new File(productAddImg.getText());
            String sourcefilename= sourcefile.getName();
            String destinationPath = new File("src/deployImg").getAbsolutePath();
            String absolutePath=destinationPath+"\\"+sourcefilename;
            
            System.out.println(absolutePath);
            boolean b= file.renameTo(new File(absolutePath));
            System.out.println(b);
            U_filePath.setText(absolutePath);
            
        }
    }
    @FXML
    private void updateProductOnClick(MouseEvent event) {
        if(productNameField.getText().length()==0){
            nameErrorLabel.setText("this field is required!");
            productNameField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else if(productBrandField.getText().length()==0){
            brandErrorLabel.setText("this field is required!");
            productBrandField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else if(productPriceField.getText().length()==0){
            priceErrorLabel.setText("this field is required!");
            productPriceField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else if(productQuantityField.getText().length()==0){
            quantityErrorLabel.setText("this field is required!");
            productQuantityField.setStyle("-fx-border-color:red;"+
                    "-fx-border-radius:10;"+
                    "-fx-background-color:#242933;"+
                    "-fx-text-fill: #ffffff;");
        }
        else{
            String name=productNameField.getText();
            float price=Float.valueOf(productPriceField.getText());
            String description=productDescriptionField.getText();
            String color=productColorField.getText();
            String brand=productBrandField.getText();
            int discount=Integer.valueOf(productDiscountField.getText());
            Boolean available= true;
            String categorie= categoriesCmbx.getValue();
            int quantite_stock=Integer.valueOf(productQuantityField.getText());
            String filePath= U_filePath.getText();
            Product p=new Product(i,name,price,description,color,brand,discount,available,categorie,quantite_stock,filePath);
            System.out.println(p);
            pcrud.update(p);
            AlertLabel.setText("product updated successfully !");
        }
    }




    
}
