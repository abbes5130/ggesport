/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Match;
import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.MatchService;
import services.Match_Team_Service;

/**
 * FXML Controller class
 *
 * @author DeadlyDaggerS
 * 
 */




public class UpdateMatchController implements Initializable {
    
    
    private int matchId;
    
     public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

     @FXML
    private AnchorPane ap;

    @FXML
    private ComboBox teamAway;
    @FXML
    private ComboBox teamHome;
    @FXML
    private TextField SeatsNumber;
    @FXML
    private TextField time;
    @FXML
    private TextField location;
    @FXML
    private TextField price;
    @FXML
    private TextField link;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                // get list of teams 
        List<Team> teams = Team.Retrieve();

        // populate choice boxes
        teamAway.setItems(FXCollections.observableArrayList(teams));
        teamAway.getSelectionModel().selectFirst();

        teamHome.setItems(FXCollections.observableArrayList(teams));
        teamHome.getSelectionModel().selectFirst();

        // TODO
        // TODO
    }    
    
    
     @FXML
    private void UpdateMatch(ActionEvent e) {
        

        MatchService matchService = new MatchService();
        Match_Team_Service matchTeam = new Match_Team_Service();


        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date.getValue());

        String Seats = SeatsNumber.getText();
        int seatsNumber = Integer.parseInt(Seats);

        String prices = price.getText();
        int gamePrice = Integer.parseInt(prices);

        Match match = new Match(matchId,Time.valueOf(time.getText()), gettedDatePickerDate, location.getText(), seatsNumber, gamePrice, link.getText());
        matchService.Update(match);
        matchTeam.DeleteMatchTeam(matchId);
        
        Team selectedAwayTeam = (Team) teamAway.getSelectionModel().getSelectedItem();
        Team selectedHomeTeam = (Team) teamHome.getSelectionModel().getSelectedItem();

        // Game selecteGame = (Game) gameSelected.getSelectionModel().getSelectedItem();
        matchTeam.Create(match.getId_match(), selectedAwayTeam.id);
        matchTeam.Create(match.getId_match(), selectedHomeTeam.id);
        System.out.println("Match updated succesfully ");
        Stage stage = (Stage) ap.getScene().getWindow();
        // do what you have to do
        //stage.close();
    }
    
    
    

}
