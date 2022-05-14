/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Role;
import entities.Users;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import services.UtilisateursServices;

/**
 * FXML Controller class
 *
 * @author ridha
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart piechart;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateursServices rs = new UtilisateursServices();
        piechart.setTitle("Les differents roles d'utilisateurs ");
        piechart.getData().setAll(new PieChart.Data("Admin", rs.calculStat()),
                new PieChart.Data("Responsable", rs.calculStat1()),
                 new PieChart.Data("membre", rs.calculStat2()));
  
    }
    
}
