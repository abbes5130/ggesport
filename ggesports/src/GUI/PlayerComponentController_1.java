/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Users;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class PlayerComponentController_1 implements Initializable {

    @FXML
     Text first_name;
    @FXML
    public Text tag_name;
    @FXML
    public Text last_name;
    @FXML
    public Text desc_id;
    
int playerId;
    @FXML
    private Button up;
    @FXML
    private Button ud;
    @FXML
    public ImageView image_play;
    public int getPlayerId(){
    return playerId;
    }
    public void setPlayerId(int playerId){
    this.playerId = playerId;
    }
       public boolean testuser(){
    if(Users.current_user.role.getRolename().equals("Responsables")){
    return true;
    }else return false;
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TeamLineController ct = new TeamLineController();
      /*  File imageFile1 = new File(ct.);
Image image = new Image(imageFile1.toURI().toString());
image_play.setImage(image);*/
        // TODO
          if(testuser()){
        up.setVisible(true);
       ud.setVisible(true);
       
       }
        else {
         up.setVisible(false);
          ud.setVisible(false);
        }
    }    
    
    
      @FXML
    private void DeletePllayer(Event e) {

        String sql = "DELETE FROM player WHERE id_player=?";
        PreparedStatement statement;

        try {
           statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            statement.setInt(1, playerId);
            statement.executeUpdate();
System.out.println(playerId);
            System.out.println("Player deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void UpdateButton(Event e) throws IOException{
    URL updatePlayerView = getClass().getResource("updatePlayer.fxml");
            FXMLLoader loader = new FXMLLoader(updatePlayerView);
               AnchorPane roott = (AnchorPane) loader.load();
               UpdatePlayerController utc = loader.getController();
               utc.setPlayerId(playerId);
               Stage upstage = new Stage();
               upstage.setTitle("Update player");
               upstage.setScene(new Scene (roott));
               upstage.show();
    }
    
}
