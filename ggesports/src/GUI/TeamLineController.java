/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Team;
import entities.Users;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
 


public class TeamLineController implements Initializable {
  
    @FXML
    public Text team_title;
    @FXML
    public Text team_desc;
    @FXML
    public Text team_pl;
   
     ScrollPane scrollpane;
int teamId;
    @FXML
    private Pane teamLL;
    @FXML
    private Button linedelete;
    @FXML
    private Button lineupdate;
    @FXML
     ImageView teamlph;
    public int getTeamId(){
    return teamId;
    }
    public void setTeamId(int teamId){
    this.teamId = teamId;
    }
     public boolean testuser(){
    if(Users.current_user.role.getRolename().equals("Responsables")){
    return true;
    }else return false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if(testuser()){
        linedelete.setVisible(true);
       lineupdate.setVisible(true);
       
       }
        else {
         linedelete.setVisible(false);
          lineupdate.setVisible(false);
        }
            
      
    }
 public void setScrollpane(ScrollPane scrollpane) {
        this.scrollpane = scrollpane;
    }    
    @FXML
    public void TeamDetails(MouseEvent e) {
        try {

            URL teamDetailsFXML = getClass().getResource("TeamD.fxml");
            FXMLLoader loader = new FXMLLoader(teamDetailsFXML);
            Pane roott = (Pane) loader.load();
            TeamDController tl = loader.getController();
           tl.teamTi.setText(team_title.getText());
           tl.TeamDes.setText(team_desc.getText());
           tl.TeamNb.setText(team_pl.getText());
           System.out.println(roott);
  
           scrollpane.setContent(roott);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void DeleteTeam(Event e) {

        String sql = "DELETE FROM team WHERE id_team=?";
        PreparedStatement statement;

        try {
           statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            statement.setInt(1, teamId);
            statement.executeUpdate();
System.out.println(teamId);
            System.out.println("Team deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void UpdateButton(Event e) throws IOException{
    URL updateTeamView = getClass().getResource("updateTeam.fxml");
            FXMLLoader loader = new FXMLLoader(updateTeamView);
               AnchorPane roott = (AnchorPane) loader.load();
               UpdateTeamController utc = loader.getController();
               utc.setTeamId(teamId);
               Stage upstage = new Stage();
               upstage.setTitle("Update team");
               upstage.setScene(new Scene (roott));
               upstage.show();
    }
  

    
}
