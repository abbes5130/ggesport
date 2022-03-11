/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ProductCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ProductDetailsController implements Initializable {

    
    @FXML
    private Label productDescriptionLabel;
    @FXML
    private Label productColorLabel;
    @FXML
    private Label productBrandLabel;
    @FXML
    private Button D_updateProductBtn;
    @FXML
    private Button D_deleteProductBtn;
    @FXML
    private Label ProductNameLabel;
    @FXML
    private Label productPriceLabel;
    @FXML
    private Label productQuantityLabel;
    @FXML
    private Label productCategoryLabel;
    @FXML
    private Label AlertLabel;
    @FXML
    private Label productReducedPriceLabel;
    
    int productId;
    ProductCRUD pcrud= new ProductCRUD();
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setValues(int i) throws IOException{
        productId=i;
        Product p= pcrud.getProductById(i);
        ProductNameLabel.setText(p.getName());
        productDescriptionLabel.setText(p.getDescription());
        productColorLabel.setText(p.getColor());
        productBrandLabel.setText(p.getBrand());
        productQuantityLabel.setText(String.valueOf(p.getQuantite_stock()));
        productPriceLabel.setText(String.valueOf(p.getPrice())+" DT");
        productCategoryLabel.setText(p.getCategorie());
        if(p.getDiscount()>0){
            float pricereduced=p.getPrice()-p.getPrice()/p.getDiscount();
            productReducedPriceLabel.setText(" / "+String.valueOf(pricereduced)+" DT");
        }
    }
        
    @FXML
    private void D_goToUpdateProductPageOnclick(MouseEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("updateProduct.fxml"));
        Pane p;
        p = fxmlloader.load();
        UpdateProductController updateProductController= fxmlloader.getController();
        updateProductController.setValues(productId);

                
        Stage stage = new Stage();
        Scene scene = new Scene(p, 1200, 1000);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void D_goToDeleteProductPageOnClick(MouseEvent event) {
        pcrud.deleteProductById(productId);
        AlertLabel.setText("product deleted successfully !");
        //AlertLabel.getScene().getWindow().hide();
    }
    

    
}
