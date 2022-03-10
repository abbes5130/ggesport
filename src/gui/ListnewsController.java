/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.actualité;
import java.awt.Color;
import static java.awt.Color.green;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.actualiteCRUD;

/**
 * FXML Controller class
 *
 * @author khale
 */
public class ListnewsController implements Initializable  {

    private Label controleSaisie;
    private TextArea contenu;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    
    private MylistenerOnPub Mylistener;
    @FXML
    private Button btnAddNew;

    /**
     * Initializes the controller class.
     */
    @Override
    @SuppressWarnings("static-access")
    public void initialize(URL url, ResourceBundle rb) {
        
                Mylistener = new MylistenerOnPub() {
                
                public void onClickListener(actualité a) {
                  FXMLLoader fxmlLoader = new FXMLLoader();
                    //System.out.println(p);
                  try{
                      
                      fxmlLoader.setLocation(getClass().getResource("/gui/actDetail.fxml"));
                      Parent root = fxmlLoader.load();
                      Stage mainStage = new Stage();
                      Scene scene = new Scene(root);
                      mainStage.setScene(scene);
                      actDetailController pubDetail_C = fxmlLoader.getController();
                      pubDetail_C.setData(a);
                      mainStage.show();
                      
                  }catch(Exception e){
                      JOptionPane.showMessageDialog(null, e);
                      
                  }
                }
                };
       afficher();
    }

    public actualiteCRUD spub = new actualiteCRUD();
    
    public void afficher(){
         
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        actualiteCRUD ps = new actualiteCRUD();
        List<actualité> L = ps.find();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < L.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/new.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ActualiteController pController = fxmlLoader.getController();
                pController.setData(L.get(i),spub,Mylistener);
                //pController.setNbreComms(L.get(i),Mylistener);
             

              
                    row++;
                               

                grid.add(anchorPane, column, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
              

               GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10, 10, 10, 10));              
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajouter(ActionEvent event) {
        
        if(contenu.getText()!= null && !contenu.getText().isEmpty()){
        actualiteCRUD P = new actualiteCRUD();
        actualité a= new actualité(contenu.getText());
        P.ajouter(a);
        afficher();
        
        Alert alertAjout = new Alert(Alert.AlertType.INFORMATION);
        
        alertAjout.setTitle("Ajout publication");
        alertAjout.setHeaderText(null);
        alertAjout.setContentText("votre publication est ajoutée avec succées");
        alertAjout.showAndWait();
        //controleSaisie.setText("Votre publication est ajoutée");
        //controleSaisie.setTextFill(color(0, 255, 0));
        }
        else{
          controleSaisie.setText("Vous n'avez saisi aucune chose pour publier  !!!");
          controleSaisie.setTextFill(color(255, 0, 0));
        }
    }

    

    @FXML
    private void actualiser(ActionEvent event) {
    afficher();
    }
    
    /*private void goToUsers(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listeUtilisateurs.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListnewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    private void clear() {
    contenu.setText("");
    }

    @FXML
    private void btnAddNew(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ajouter news.fxml"));
             Scene scene = new Scene(loader.load(), 700, 700);
        Stage stage = new Stage();
        stage.setTitle("ajouterpage");
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterNewsController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    
}
