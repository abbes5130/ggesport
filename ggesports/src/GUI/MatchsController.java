/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Match;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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

    /**
     * Initializes the controller class.
     */
    public void fillList() {
        MatchService matchService = new MatchService();
        List<Match> matches = matchService.Retrieve();
        System.out.print("Matches " + matches.toString());
        vBox.getChildren().clear();

        for (Match matche : matches) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MatchComponent.fxml"));
                HBox root = (HBox) loader.load();
                MatchComponentController ctrl = loader.getController();
                ctrl.matchTime.setText(matche.getTime().toString());
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
