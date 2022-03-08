/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Match;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.MatchService;
import services.Match_Team_Service;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.MyDB;

class Team {

    public static List<Team> Retrieve() {
        List<Team> ListTeam = new ArrayList<Team>();
        Connection cnx2;

        cnx2 = MyDB.getInstance().getCnx();

        try {
            Statement statement;
            statement = cnx2.createStatement();
            String req = "SELECT id_equipe,nom FROM `equipe`";
            ResultSet rst;
            rst = statement.executeQuery(req);

            while (rst.next()) {
                Team t = new Team();

                t.id = rst.getInt(1);
                t.team = rst.getString(2);

                ListTeam.add(t);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListTeam;
    }


    public Team() {
    }

    String team;
    int id;

    Team(int id, String team) {
        this.id = id;
        this.team = team;
    }

    @Override
    public String toString() {
        return team;
    }
}


/**
 * FXML Controller class
 *
 * @author DeadlyDaggerS
 */
public class AddMatchController implements Initializable {

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
    }

    @FXML
    private void addNewMatch(ActionEvent e) {
        System.out.println("c addNewMatch");

        MatchService matchService = new MatchService();
        Match_Team_Service matchTeam = new Match_Team_Service();
        System.out.println("b addNewMatch");

        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date.getValue());
        System.out.println("Time is");
        System.out.println(time.getText());
        System.out.println(Time.valueOf(time.getText()));
        System.out.println("Date is");
        System.out.println(gettedDatePickerDate);
        System.out.println("Location is");
        System.out.println(location.getText());

        System.out.println("nb seat is");
        System.out.println(SeatsNumber.getText());
        String Seats = SeatsNumber.getText();
        int seatsNumber = Integer.parseInt(Seats);

        System.out.println("price is");
        System.out.println(price.getText());
        String prices = price.getText();
        int gamePrice = Integer.parseInt(prices);
        System.out.println("Link is");
        System.out.println(link.getText());

        Match match = new Match(Time.valueOf(time.getText()), gettedDatePickerDate, location.getText(), seatsNumber, gamePrice, link.getText());
        int createdMatchId = matchService.CreateMatch(match);
        if (createdMatchId == -1) {
            System.out.println("Could not find match");
            return;
        }
        Team selectedAwayTeam = (Team) teamAway.getSelectionModel().getSelectedItem();
        Team selectedHomeTeam = (Team) teamHome.getSelectionModel().getSelectedItem();

        // Game selecteGame = (Game) gameSelected.getSelectionModel().getSelectedItem();
        matchTeam.Create(createdMatchId, selectedAwayTeam.id);
        matchTeam.Create(createdMatchId, selectedHomeTeam.id);
        System.out.println("Match created succesfully " + createdMatchId);
        Stage stage = (Stage) ap.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
