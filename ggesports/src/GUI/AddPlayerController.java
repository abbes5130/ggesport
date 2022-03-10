/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Player;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.GameCRUD;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class AddPlayerController {
 private ObservableList<Player> appMainObservableList_upa;
    @FXML
     private AnchorPane ptpadd;
    @FXML
    private TextField tmfield_upa;
    @FXML
    private TextField tmfield1_upa;
    @FXML
    private TextField lgfield_upa;
    @FXML
    private TextField plfield_upa;
    @FXML
    private TextField dsfield_upa;
 
  
    
       //fill table button click event
    @FXML
    void fillTable_player(ActionEvent event) {

         Player t = new Player();
        String sql = "INSERT INTO player (id_team, firstname, lastname, description, player_tag, photo) VALUES (?, ?, ?, ?, ?, ?)";
 
PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
statement.setInt(1,16);
            statement.setString(2,tmfield_upa.getText());
            
statement.setString(3,tmfield1_upa.getText());
statement.setString(4, lgfield_upa.getText());
statement.setString(5,plfield_upa.getText());
statement.setString(6, dsfield_upa.getText());
       statement.executeUpdate();
       System.out.println("created new player");
            
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    
    }

    public void setAppMainObservableList(ObservableList<Player> tvObservableList) {
        this.appMainObservableList_upa = tvObservableList;

    }
    
  
    
    
}
