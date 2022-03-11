/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ProductCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ProductItemController implements Initializable {
    
    @FXML
    private ImageView productImage;
    @FXML
    private Label productDescriptionLabel;
    @FXML
    private Label productPriceLabel;
    @FXML
    private Label viewDetailsLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productIdLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setValues(Product p){
        productNameLabel.setText(p.getName());
        productDescriptionLabel.setText(p.getDescription());
        productPriceLabel.setText(Float.toString(p.getPrice())+" DT");
        productIdLabel.setText(String.valueOf(p.getId()));
        //System.out.println(p.getImage());
        
        //Image image=new Image(getClass().getResourceAsStream(p.getImage()));
        //productImage.setImage(new Image(p.getImage()));
//        System.out.println("hhhh");        
//        System.out.println("hhhhhh");

    }

    @FXML
    private void ViewDetailsOnclick(MouseEvent event) throws IOException{

        Object o= event.getSource();
        System.out.println(o instanceof Label);
        Label label= (Label)o;
        VBox vb=(VBox)label.getParent();
        System.out.println(vb instanceof VBox);        
        List<Node> nodelist= new ArrayList();
        nodelist=vb.getChildren();
        String id = ((Label)nodelist.get(5)).getText();
        int i= Integer.valueOf(id);
        System.out.println(id);

        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("ProductDetails.fxml"));
        Pane p;
        p = fxmlloader.load();
        ProductDetailsController productDetailsController= fxmlloader.getController();
        productDetailsController.setValues(i);

                
        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("ProductDetails.fxml"));     
        Scene scene = new Scene((Parent)p, 1200, 1000);
        stage.setScene(scene);
        stage.show();
          
//        System.out.println("helllloooooooo");
//        System.out.println(productIdLabel.getText());
//        String productIdStr=productIdLabel.getText();
//        int productIdInt=Integer.parseInt(productIdStr);
//        ProductCRUD pcrud= new ProductCRUD();
//        Product p= new Product();

//        p=pcrud.getProductById(productIdInt);
//        FXMLLoader mainloader= new FXMLLoader();
//        mainloader.setLocation(getClass().getResource("main.fxml"));
//        Node node=mainloader.load();
//        MainController maincontroller= mainloader.getController();
//        maincontroller.setProductDetailsView(p);

            

//        System.out.println("helllloooooooo");            
//        Parent root= FXMLLoader.load(getClass().getResource("main.fxml"));
//        Scene scene = new Scene(root);
//        Node node=(Node)scene.lookup("#ProductDetails");
//        System.out.println((Node)scene.lookup("#ProductDetails"));
//        node.setManaged(true);
//        node.setVisible(true);
//        System.out.println("helllloooooooo");
//        System.out.println("helllloooooooo");
        

//        String name = ((Label)nodelist.get(2)).getText();
//        String description = ((Label)nodelist.get(3)).getText();
//        String price = ((Label)nodelist.get(4)).getText();
//        String  color= ((Label)nodelist.get(5)).getText();
//        String i=lab.getId();
//        String s = lab.getText();
//        System.out.println(s);
        
        //Node n= (Node)p.getChildren().get(1);
//        String productName= this.productNameLabel.getText();
//        float price = Float.valueOf(this.productPriceLabel.getText());
//        String description = this.productDescriptionLabel.getText();
//        String color= "black";
//        String brand= "csgo";
//        int quantity= 10;
//        Product p= new Product(productName,price,description,color,brand,quantity);
//        FXMLLoader productDetailsloader = new FXMLLoader();
//        productDetailsloader.setLocation(getClass().getResource("productDetails.fxml"));
//        AnchorPane anchor= productDetailsloader.load();
//        ProductDetailsController productDetails= productDetailsloader.getController();
//        productDetails.setValues(p);
//        FXMLLoader mainloader= new FXMLLoader();
//        mainloader.setLocation(getClass().getResource("main.fxml"));
//        AnchorPane anchorpane=mainloader.load();
//        MainController maincontroller= mainloader.getController();
//        maincontroller.setProductDetailsView(anchor);
    }
    
    
}
