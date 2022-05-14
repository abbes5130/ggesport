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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MainController implements Initializable {

    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Label lbluser;
    @FXML
    private Label lblrole;
    @FXML
    private ImageView setting;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
lbluser.setText(Users.current_user.getLastname());
lblrole.setText(Users.current_user.role.getRolename());
    }

    @FXML
    public void home(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML

    private void teams(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamView.fxml"));
        Pane root = (Pane) loader.load();
        TeamViewController tl = loader.getController();
        tl.setScrollpane(scrollpane);
        scrollpane.setContent(root);

    }

    @FXML
    private void matchs(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("matchs.fxml"));
        Pane root = (Pane) loader.load();
        MatchsController ctrl = loader.getController();
       ctrl.setScrollpane(scrollpane);
        
        scrollpane.setContent(root);
    }

    @FXML
    private void news(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("news.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void store(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("store.fxml"));
        scrollpane.setContent(fxml);
    }

    @FXML
    private void setting(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        scrollpane.setContent(fxml);
    }
}