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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import services.MatchService;
import services.Match_Team_Service;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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

    @FXML
    private Label errNbSeats;
    @FXML
    private Label errLocation;
    @FXML
    private Label errPrice;
    @FXML
    private Label errLink;
    private boolean verificationNbSeats;
    private boolean verificationLocation;
    private boolean verificationPrice;
    private boolean verificationLink;

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
        

        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date.getValue());

        String Seats = SeatsNumber.getText();
        int seatsNumber = Integer.parseInt(Seats);

        String prices = price.getText();
        int gamePrice = Integer.parseInt(prices);


        Match match = new Match(Time.valueOf(time.getText()), gettedDatePickerDate, location.getText(), seatsNumber, gamePrice, link.getText());
        int createdMatchId = matchService.CreateMatch(match);
        if (createdMatchId == -1) {
            System.out.println("Could not find match");
            return;
        }
        Team selectedAwayTeam = (Team) teamAway.getSelectionModel().getSelectedItem();
        Team selectedHomeTeam = (Team) teamHome.getSelectionModel().getSelectedItem();

        
            matchTeam.Create(createdMatchId, selectedAwayTeam.id);
            matchTeam.Create(createdMatchId, selectedHomeTeam.id);
            System.out.println("Match created succesfully " + createdMatchId);
            Stage stage = (Stage) ap.getScene().getWindow();
            // do what you have to do
            stage.close();
        

    }

    @FXML
    private boolean verificationNbSeats(KeyEvent event) {

        int nbNonChar = 0;
        for (int i = 1; i < SeatsNumber.getText().trim().length(); i++) {
            char ch = SeatsNumber.getText().charAt(i);
            if (!Character.isDigit(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && SeatsNumber.getText().trim().length() >= 1) {
            errNbSeats.setText("");

            verificationNbSeats = true;
        } else {
            errNbSeats.setText("Il faut entrer un nombre");
            verificationNbSeats = false;

        }
        return verificationNbSeats;
    }

    @FXML
    private boolean verificationLocation(KeyEvent event) {

        int nbNonChar = 0;
        for (int i = 1; i < location.getText().trim().length(); i++) {
            char ch = location.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && location.getText().trim().length() >= 3) {
            errLocation.setText("");

            verificationLocation = true;
        } else {
            errLocation.setText("Il faut au moins 3 caracteres");
            verificationLocation = false;

        }
        return verificationLocation;
    }
    
        @FXML
    private boolean verificationPrice(KeyEvent event) {

        int nbNonChar = 0;
        for (int i = 1; i < price.getText().trim().length(); i++) {
            char ch = price.getText().charAt(i);
            if (!Character.isDigit(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && price.getText().trim().length() >= 1) {
            errPrice.setText("");

            verificationPrice = true;
        } else {
            errPrice.setText("Il faut entrer un nombre");
            verificationPrice = false;

        }
        return verificationPrice;
    }
   
        @FXML
    private boolean verificationLink(KeyEvent event) {

        int nbNonChar = 0;
        for (int i = 1; i < link.getText().trim().length(); i++) {
            char ch = link.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && link.getText().trim().length() >= 3) {
            errLink.setText("");

            verificationLink = true;
        } else {
            errLink.setText("Il faut au moins 3 caracteres");
            verificationLink = false;

        }
        return verificationLink;
    }

}
