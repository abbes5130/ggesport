/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Player;
import entities.Team;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.PlayerCRUD;
import services.TeamCRUD;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class TeamDController implements Initializable {

    @FXML
     Text teamTi;
    @FXML
     Text TeamDes;
    @FXML
     Text TeamNb;
    @FXML
    private VBox myVboxTD;
private int teamId;
    @FXML
    private ImageView iddd;
    public int getTeamId(){
    return teamId;
    }
    public void setTeamId(int teamId){
    this.teamId = teamId;
    }
    
     public void loadDetails(int teamId) {
         
        // Load details from teamId
        PreparedStatement statement;
        String sql = "SELECT team_name,logo,players_number,description FROM `team` WHERE team.id_team=?";

        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
            statement.setInt(1, teamId);

            ResultSet rst;
            rst = statement.executeQuery();

            while (rst.next()) {
                 
                   String u ;
                  u = rst.getString("logo");
                  System.out.println(u);
                 String imageFile1 = new File(u).toURI().toString();
                teamTi.setText(rst.getString("team_name"));
                iddd.setImage(new Image(imageFile1));
                TeamNb.setText(rst.getString("players_number"));
                TeamDes.setText(rst.getString("description"));
            
               
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       PlayerCRUD playerCRUD = new PlayerCRUD();
       TeamLineController tl = new TeamLineController();
        List<Player> players = playerCRUD.RetrieveById(1);
        System.out.println("cnx done");
        System.out.println("Players : "+ players.toString());
     myVboxTD.getChildren().clear();
            
          
         
        for (int i=0; i<players.size();i++){
            Player player1 = players.get(i);
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerComponent.fxml"));
      Pane root = (Pane) loader.load();
      
     PlayerComponentController pc = loader.getController();
                   
                     pc.first_name.setText(player1.getName());
                     pc.last_name.setText(player1.getSurname());
                     pc.desc_id.setText(player1.getDescription());
                     pc.tag_name.setText(player1.getTag());
                     myVboxTD.getChildren().add(root);
                     
                   
        }catch(IOException e){
        System.out.println(e.getMessage());   
        }
        
        }}
}
            
    
