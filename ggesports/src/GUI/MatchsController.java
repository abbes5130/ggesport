/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Match;
import entities.MatchResultWithTeam;
import entities.Users;
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.MatchService;
import utils.MyDB;
import utils.db;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class MatchsController implements Initializable {

    @FXML
    private Button adm;

    public boolean testuser() {
        if (Users.current_user.role.getRolename().equals("Responsables")) {
            return true;
        } else {
            return false;
        }
    }
    @FXML
    VBox vBox;

    Pane pane;
    private ArrayList<MatchComponentController> controllers;

    public void setScrollpane(ScrollPane scrollpane) {
        System.out.println("Setting scroll pane to");
        for (MatchComponentController controller : controllers) {
            controller.setScrollpane(scrollpane);
        }

    }

    public Team loadTeam(int teamId) {

        // Load details from teamId
        PreparedStatement statement;

        Team t = new Team();
        String sql = "SELECT team_name,logo FROM `team` WHERE team.id_team=?";

        try {

            statement = (PreparedStatement) new MyDB().getCnx().prepareStatement(sql);
            statement.setInt(1, teamId);

            ResultSet rst;
            rst = statement.executeQuery();

            while (rst.next()) {
                t.id = teamId;
                t.team = (rst.getString("team_name"));
                t.logo = rst.getString("logo");

                break;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;

    }

    public void fillList() {
        MatchService matchService = new MatchService();
        List<Match> matches = matchService.Retrieve();
        vBox.getChildren().clear();
        controllers = new ArrayList<>();
        for (int i = 0; i < matches.size(); i += 1) {

            try {
                Match match = matches.get(i);
                Team team1 = loadTeam(match.getIdTeam1());
                Team team2 = loadTeam(match.getIdTeam2());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchComponent.fxml"));
                Pane root = (Pane) loader.load();
                MatchComponentController ctrl = loader.getController();
                controllers.add(ctrl);
                ctrl.setMatchId(match.getId_match());
                ctrl.matchTime.setText(match.getTime().toString());
                ctrl.matchDate.setText(match.getDate().toString());
                ctrl.HomeTeam.setText(team1.team);
                ctrl.AwayTeam.setText(team2.team);

                try {
                    String imgFile1 = new File(team1.logo).toURI().toString();
                    ctrl.HomeImage.setImage(new Image(imgFile1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    String imgFile2 = new File(team2.logo).toURI().toString();
                    ctrl.AwayImage.setImage(new Image(imgFile2));
                } catch (Exception e) {

                }

                vBox.getChildren().add(root);

            } catch (Exception e) {

            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (testuser()) {
            adm.setVisible(true);

        } else {
            adm.setVisible(false);

        }
        this.fillList();
    }

    @FXML
    private void addMatchAction(ActionEvent e) {
        try {

            Parent root = FXMLLoader.load(TestFxml.class.getResource("AddMatch.fxml"));
            Stage secondStage = new Stage();
            secondStage.setTitle("Add Match Modal");
            secondStage.setScene(new Scene(root));
            secondStage.show();
            secondStage.setOnHidden(event -> {
                this.fillList();
            });

        } catch (Exception ex) {
            System.out.println("An error happened :(");
            System.out.println(ex.getMessage());

        }
    }

}
