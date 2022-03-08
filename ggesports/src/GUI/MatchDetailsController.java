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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author DeadlyDaggerS
 */
public class MatchDetailsController implements Initializable {

    Connection cnx2;

    public MatchDetailsController() {
        cnx2 = MyDB.getInstance().getCnx();
    }
    @FXML
    Label AwayTeam;
    @FXML
    Label HomeTeam;
    @FXML
    Label matchTime;
    @FXML
    Label matchDate;
    @FXML
    Label matchPlace;
    @FXML
    Label matchPrice;
    @FXML
    ImageView HomeImage;
    @FXML
    ImageView AwayImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loadDetails(int matchId) {
        // Load details from matchId
        PreparedStatement statement;
        String req = "SELECT price,location,nb_place_dispo FROM `matchs` WHERE matchs.id_match=?";

        try {
            statement = cnx2.prepareStatement(req);
            statement.setInt(1, matchId);

            ResultSet rst;
            rst = statement.executeQuery();

            while (rst.next()) {
                int price = rst.getInt("price");
                matchPrice.setText("" + price);
                String location = rst.getString("location");
                matchPlace.setText("" + location);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // Create pane for player details.
    }

}
