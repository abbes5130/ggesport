/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Match;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.MatchService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author DeadlyDaggerS
 */
public class MatchComponentController implements Initializable {

    Connection cnx2;

    public MatchComponentController() {
        cnx2 = MyDB.getInstance().getCnx();
    }

    private ScrollPane scrollpane;
    private int matchId;
    @FXML
    Label AwayTeam;
    @FXML
    Label HomeTeam;
    @FXML
    Label matchTime;
    @FXML
    Label matchDate;
    @FXML
    ImageView HomeImage;
    @FXML
    ImageView AwayImage;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScrollpane(ScrollPane scrollpane) {
        this.scrollpane = scrollpane;
    }

    @FXML
    private void MatchDetails(MouseEvent e) {
        try {

            URL matchDetailsFXML = getClass().getResource("MatchDetails.fxml");
            FXMLLoader loader = new FXMLLoader(matchDetailsFXML);
            Pane root = (Pane) loader.load();
            MatchDetailsController ctrl = loader.getController();
            ctrl.AwayTeam.setText(AwayTeam.getText());
            ctrl.HomeTeam.setText(HomeTeam.getText());
            ctrl.matchTime.setText(matchTime.getText());
            ctrl.matchDate.setText(matchDate.getText());
            ctrl.HomeImage.setImage(HomeImage.getImage());
            ctrl.AwayImage.setImage(AwayImage.getImage());
            ctrl.loadDetails(matchId);
            ctrl.getIdmatch(matchId);
            
            System.out.println(scrollpane);
            System.out.println(root);
            scrollpane.setContent(root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void DeleteMatch(MouseEvent e) {

        String req = "DELETE FROM matchs WHERE id_match=?";
        PreparedStatement statement;

        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1, matchId);
            statement.executeUpdate();

            System.out.println("Match deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void UpdateMatchView(MouseEvent e) {
        try {

            URL matchDetailsFXML = getClass().getResource("UpdateMatch.fxml");
            FXMLLoader loader = new FXMLLoader(matchDetailsFXML);
            AnchorPane root = (AnchorPane) loader.load();
            UpdateMatchController ctrl = loader.getController();
            ctrl.setMatchId(matchId);
            Stage secondStage = new Stage();
            secondStage.setTitle("Update Match Modal");
            secondStage.setScene(new Scene(root));
            secondStage.show();

        } catch (Exception ex) {
            System.out.println("An error happened :(");
            ex.printStackTrace();

        }
    }

}

