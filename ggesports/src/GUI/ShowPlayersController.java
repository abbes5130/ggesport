          /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Player;
import entities.Team;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.PlayerCRUD;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class ShowPlayersController implements Initializable {
    
    
     
  @FXML
    void onOpenDialog_addplayer(ActionEvent event) throws IOException {
        System.out.println("onOpenDialog clicked");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPlayer.fxml"));
        Parent parent = fxmlLoader.load();
        AddPlayerController dialogController = fxmlLoader.<AddPlayerController>getController();
      
        Scene scene = new Scene(parent, 1000, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       
       
       
        

    }
    @FXML
    private VBox playersVBOX;
private ArrayList<PlayerComponentController_1> controllers;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       PlayerCRUD playerCRUD = new PlayerCRUD();
       PlayerComponentController_1 tl = new PlayerComponentController_1();
        List<Player> players = playerCRUD.Retrieve();
        System.out.println("cnx done");
        System.out.println("Players : "+ players.toString());
     playersVBOX.getChildren().clear();
            controllers = new ArrayList<>();
          
         
        for (int i=0; i<players.size();i++){
            Player player1 = players.get(i);
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerComponent_1.fxml"));
      Pane root = (Pane) loader.load();
      
     PlayerComponentController_1 pc = loader.getController();
                   controllers.add(pc);
                   pc.setPlayerId(player1.getId_player());
                     pc.first_name.setText(player1.getName());
                     pc.last_name.setText(player1.getSurname());
                     pc.desc_id.setText(player1.getDescription());
                     pc.tag_name.setText(player1.getTag());
                     playersVBOX.getChildren().add(root);
                     
                   
        }catch(IOException e){
        System.out.println(e.getMessage());   
        }
        
    }    
    
}
}