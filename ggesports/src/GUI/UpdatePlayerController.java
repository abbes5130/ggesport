/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Player;
import entities.Team;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.GameCRUD;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class UpdatePlayerController implements Initializable {
     private ObservableList<Team> appMainObservableList_up;
     private AnchorPane pt;
      TextField tmfield_up;
      TextField tmfield1_up;
    TextField lgfield_up;
    TextField plfield_up;
    TextField dsfield_up;
int playerId;
    public int getPlayerId(){
    return playerId;
    }
    public void setPlayerId(int playerId){
    this.playerId = playerId;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     //fill table button click event
    @FXML
    void updatePlayer(Event event) {

       String sql = "UPDATE player SET firstname=?, lastname=?, description=?, player_tag=?, photo=? WHERE id_player=?";

 Player pp = new Player();
 
 System.out.println(playerId);
 
PreparedStatement statement;
       try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);

            statement.setString(1,tmfield_up.getText());
            statement.setString(2,tmfield1_up.getText());
statement.setString(3,lgfield_up.getText());
statement.setString(4, plfield_up.getText());
statement.setString(5,dsfield_up.getText());

statement.setInt(5, playerId);
       statement.executeUpdate();
       System.out.println("updated team");
            Stage stage = (Stage) pt.getScene().getWindow();   
            // do what you have to do       
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       
    
    } 
     public void setAppMainObservableList_u(ObservableList<Team> tvObservableList) {
        this.appMainObservableList_up = tvObservableList;

    }
    
}
