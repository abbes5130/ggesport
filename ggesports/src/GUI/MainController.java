
package gui;

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
import javafx.scene.control.ScrollPane;



public class MainController implements Initializable {

    
    @FXML
    private ScrollPane scrollpane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }    
    
    @FXML
    public void home(ActionEvent actionEvent)throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/homePage.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void teams(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/teams.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void matchs(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/matchs.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void news(ActionEvent event) throws IOException{
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/GUI/listnews.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void store(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/GUI/store.fxml"));
        scrollpane.setContent(fxml);
    }
}
