/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.commentaireCRUD;

/**
 * FXML Controller class
 *
 * @author khale
 */
public class CommentaireDetailController implements Initializable {

    @FXML
    private TextField contenu;
    @FXML
    private Button supprimer;
    
    @FXML
    private TextField id_actualité;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private Commentaire commentaire;
    
    public void setData(Commentaire com) {
        this.commentaire = com;
        contenu.setText(com.gettexte());
        

    }
    @FXML
    private void modifier(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Modification Commentaire");
        alert.setHeaderText("Etes vous sure de modifier le commentaire?");
        
      Optional<ButtonType> option = alert.showAndWait();
      
      if (option.get() == ButtonType.OK) {
        commentaireCRUD ps = new commentaireCRUD();
        commentaire.settexte(contenu.getText());
int i = Integer.parseInt(id_actualité.getText());
        commentaire.setId_actualite(i);
        ps.Updatecommmentaire2(commentaire);
      }
      else{
        //commentaire.setContenue(commentaire.getContenue());
        return;
      }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression Commentaire");
        alert.setHeaderText("Etes vous sure de supprimer le commentaire?");
        
      Optional<ButtonType> option = alert.showAndWait();
      
      if (option.get() == ButtonType.OK) {
        commentaireCRUD ps = new commentaireCRUD();
        ps.deletecommmentaire2(commentaire);
        
        Stage stage = (Stage) supprimer.getScene().getWindow();
        stage.close();
      }
      else{
        return;
      }
        
    }
    
    private void returnToPub(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("actDetail.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
