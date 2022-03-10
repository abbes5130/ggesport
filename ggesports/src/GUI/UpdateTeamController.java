/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Team;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class UpdateTeamController  {
     private ObservableList<Team> appMainObservableList_u;
    TextField tmfield_u;
    TextField lgfield_u;
    TextField plfield_u;
    TextField dsfield_u;
    
    private int teamId;
    private AnchorPane ppp;
    @FXML
    private AnchorPane pt;
    @FXML
    private TextField tmfield_up;
    @FXML
    private TextField lgfield_up;
    @FXML
    private TextField plfield_up;
    @FXML
    private TextField dsfield_up;
    private int getTeamId(){
    return teamId;
    }
public void setTeamId(int teamId){
this.teamId = teamId;
}
@FXML
     //fill table button click event
 public   void updateTeam(Event event) {

       String sql = "UPDATE team SET team_name=?, logo=?, players_number=?, description=? WHERE id_team=?";

 Team tt = new Team();
 
 System.out.println(teamId);
 
PreparedStatement statement;
       try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);

            statement.setString(1,tmfield_u.getText());
            
statement.setString(2,lgfield_u.getText());
statement.setString(3, plfield_u.getText());
statement.setString(4,dsfield_u.getText());

statement.setInt(5, teamId);
       statement.executeUpdate();
       System.out.println("updated team");
            Stage stage = (Stage) ppp.getScene().getWindow();   
            // do what you have to do       
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       
    
    } 
     public void setAppMainObservableList_u(ObservableList<Team> tvObservableList) {
        this.appMainObservableList_u = tvObservableList;

    }

   
    
}
