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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.GameCRUD;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class AddTeamController {

     private ObservableList<Team> appMainObservableList;
@FXML
public TextField tmfield;
@FXML
public TextField lgfield;
@FXML
public TextField plfield;
@FXML
public TextField dsfield;

    //fill table button click event
    @FXML
    void fillTable(ActionEvent event) {

         Team t = new Team();
        String sql = "INSERT INTO team (team_name, logo, players_number, description) VALUES (?, ?, ?, ?)";
 
PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);

            statement.setString(1,tmfield.getText());
            
statement.setString(2,lgfield.getText());
statement.setString(3, plfield.getText());
statement.setString(4,dsfield.getText());
       statement.executeUpdate();
       System.out.println("created new team");
            
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    
    }

    public void setAppMainObservableList(ObservableList<Team> tvObservableList) {
        this.appMainObservableList = tvObservableList;

    }
    
}
