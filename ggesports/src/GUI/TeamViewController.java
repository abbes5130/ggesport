/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Team;
import entities.Users;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import services.TeamCRUD;
import utils.db;



public class TeamViewController implements Initializable {
    


    
    @FXML
    private VBox itemlistt ;

    //onclick modal
ObservableList<Team> teamL = FXCollections.observableArrayList();
   Pane pane;
    private ArrayList<TeamLineController> controllers;
    @FXML
    private Button teamadd_button;

    public void setScrollpane(ScrollPane scrollpane) {
        System.out.println("SEetting scroll pane to");
        
        for (TeamLineController controller : controllers) {
            controller.setScrollpane(scrollpane);
        }

    }

    public boolean testuser(){
    if(Users.current_user.role.getRolename().equals("Responsables")){
    return true;
    }else return false;
    }
    
    
    @FXML
    void onOpenDialog(ActionEvent event) throws IOException {
        System.out.println("onOpenDialog clicked");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addTeam.fxml"));
        Parent parent = fxmlLoader.load();
        AddTeamController dialogController = fxmlLoader.<AddTeamController>getController();
        dialogController.setAppMainObservableList(teamL);
        Scene scene = new Scene(parent, 1000, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       
       
       
        

    }
    /*   @FXML
    void onOpenDialog1(ActionEvent event) throws IOException {
        System.out.println("onOpenDialog clicked");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateTeam.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateTeamController dialogController = fxmlLoader.<UpdateTeamController>getController();
        dialogController.setAppMainObservableList_u(teamL);
        Scene scene = new Scene(parent, 1000, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       
       
       
        

    }*/
       @FXML
    void onOpenDialog2(ActionEvent event) throws IOException {
        System.out.println("onOpenDialog2 clicked");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showPlayers.fxml"));
        Parent parent = fxmlLoader.load();
        ShowPlayersController dialogController = fxmlLoader.<ShowPlayersController>getController();
        
        Scene scene = new Scene(parent, 1000, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       
       
       
        

    }
    
    public ObservableList<Team> getTvObservableList() {
        return teamL;
    }

    
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        if(testuser()){
        teamadd_button.setVisible(true);}
        else {
         teamadd_button.setVisible(false);
        }
        
        TeamCRUD teamCrud = new TeamCRUD();
        List<Team> teamss = teamCrud.Retrieve();
        System.out.println("cnx done");
        System.out.println("Teams : "+ teamss.toString());
     itemlistt.getChildren().clear();
            controllers = new ArrayList<>();
          
         
        for (int i=0; i<teamss.size();i++){
            Team team1 = teamss.get(i);
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamLine.fxml"));
      Pane root = (Pane) loader.load();
      
     TeamLineController tl = loader.getController();
     controllers.add(tl);
       File imageFile1 = new File(team1.getLogo());
                   
                   Image image = new Image(imageFile1.toURI().toString());
     tl.setTeamId(team1.getId_team());
                     tl.team_title.setText(team1.getName())  ;
                     tl.team_desc.setText(team1.getDescription());
                     tl.team_pl.setText(""+team1.getNb_joueur());
                     tl.teamlph.setImage(image);
                     itemlistt.getChildren().add(root);
               
        }catch(IOException e){
        System.out.println(e.getMessage());   
        }
        
        }
    
    
    
    
    
    }


}
            

   
