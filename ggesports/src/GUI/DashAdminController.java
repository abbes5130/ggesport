
package GUI;

import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;



public class DashAdminController implements Initializable {

    
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Label lblnomprenom;
    @FXML
    private Label lblrole;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        lblnomprenom.setText(Users.current_user.getFirstname());
        lblrole.setText(Users.current_user.role.getRolename());
    }    
    
   

    @FXML
    private void user(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Userspage.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void role(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("RolesPage.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void stats(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void settings(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        scrollpane.setContent(fxml);
    }
}
