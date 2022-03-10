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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ProductDetailsController implements Initializable {

    
    private Label productNameLabel;
    @FXML
    private Label productDescriptionLabel;
    private Label productColorLabel;
    private Label productBrandLabel;
    private Label quantityLabel;
    @FXML
    private Label D_productNameLabel;
    @FXML
    private Button D_updateProductBtn;
    @FXML
    private Button D_deleteProductBtn;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setValues(Product p) throws IOException{
        productNameLabel.setText(p.getName());
        productDescriptionLabel.setText(p.getDescription());
        productColorLabel.setText(p.getColor());
        productBrandLabel.setText(p.getBrand());
        quantityLabel.setText(String.valueOf(p.getQuantite_stock()));
//        FXMLLoader mainloader= new FXMLLoader();
//        mainloader.setLocation(getClass().getResource("main.fxml"));
//        AnchorPane anchorpane= mainloader.load();
//        MainController maincontroller= mainloader.getController();
    }
        
    @FXML
    private void D_goToUpdateProductPageOnclick(MouseEvent event) {
    }

    @FXML
    private void D_goToDeleteProductPageOnClick(MouseEvent event) {
    }
    

    
}
