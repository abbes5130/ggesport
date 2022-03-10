/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Match;
import entities.MatchResultWithTeam;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.MatchService;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class MatchsController implements Initializable {

    @FXML
    VBox vBox;

    @FXML
    Pane pane;
    private ArrayList<MatchComponentController> controllers;

    public void setScrollpane(ScrollPane scrollpane) {
        System.out.println("SEetting scroll pane to");
        for (MatchComponentController controller : controllers) {
            controller.setScrollpane(scrollpane);
        }

    }

    /**
     * Initializes the controller class.
     */
    public void fillList() {
        MatchService matchService = new MatchService();
        List<MatchResultWithTeam> matchesWithTeam = matchService.RetrieveWithTeams();
        vBox.getChildren().clear();
        controllers = new ArrayList<>();
        for (int i = 0; i < matchesWithTeam.size(); i += 2) {
            MatchResultWithTeam result1 = matchesWithTeam.get(i);
            MatchResultWithTeam result2 = matchesWithTeam.get(i + 1);
            try {
                String imgFile1 = new File(result1.getLogo()).toURI().toString();
                String imgFile2 = new File(result2.getLogo()).toURI().toString();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchComponent.fxml"));
                Pane root = (Pane) loader.load();
                MatchComponentController ctrl = loader.getController();
                controllers.add(ctrl);
                ctrl.setMatchId(result1.getId_match());
                ctrl.matchTime.setText(result1.getTime().toString());
                ctrl.matchDate.setText(result1.getDate().toString());
                ctrl.HomeTeam.setText(result1.getTeam_name());
                ctrl.AwayTeam.setText(result2.getTeam_name());
                ctrl.HomeImage.setImage(new Image(imgFile1));
                ctrl.AwayImage.setImage(new Image(imgFile2));

                vBox.getChildren().add(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
