/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ridha
 */
public class DashbordController implements Initializable {

    @FXML
    private Button btnhome;
    @FXML
    private Button btnusers;
    @FXML
    private Button btnrole;
    @FXML
    private Button btnstats;
    @FXML
    private BorderPane contentArea;
    @FXML
    private AnchorPane bp;
    @FXML
    private Label lblnomuser;
    @FXML
    private Label lblprenomuser;
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblnomuser.setText(Users.current_user.getFirstname());
        lblprenomuser.setText(Users.current_user.getLastname());
    }    

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void Home(ActionEvent event) {
        contentArea.setCenter(bp);
    }

    @FXML
    private void Users_page(ActionEvent event) throws IOException {
        LoadPage("Userpage");
        
    }

    @FXML
    private void Roles_page(ActionEvent event) throws IOException {
        LoadPage("RolesPage");
    }

    @FXML
    private void Stats_page(ActionEvent event) throws IOException {
        LoadPage("Statistique");
    }
    private void LoadPage(String page) throws IOException{
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        contentArea.setCenter(root);
    }
    
}
