/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Team;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import services.GameCRUD;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class AddTeamController {

     private ObservableList<Team> appMainObservableList;
@FXML
public TextField tmfield;
@FXML
public TextField lgfield;
@FXML
public TextField plfield;
   private File file;
       private FileInputStream fileInput;

    private FileOutputStream fileOutput;

    private byte[] userImage;
       private String imgPath;
    private String logimgPath;
@FXML
public TextField dsfield;
private boolean verifPlayername1;
private boolean verifPlayername2;

@FXML
    private Label labeltm;
    @FXML
    private Label labelpl;

    //fill table button click event
    @FXML
    void fillTable(ActionEvent event) {

         Team t = new Team();
        String sql = "INSERT INTO team (team_name, logo, players_number, description) VALUES (?, ?, ?, ?)";
 
PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);

            statement.setString(1,tmfield.getText());
            
statement.setString(2,lgfield.getText());
statement.setString(3, plfield.getText());
statement.setString(4,dsfield.getText());
       statement.executeUpdate();
       System.out.println("created new team");
            
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    
    }

    public void setAppMainObservableList(ObservableList<Team> tvObservableList) {
        this.appMainObservableList = tvObservableList;

    }
        @FXML
    public void attachImageOnAction1(ActionEvent event) throws IOException {
          FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (.jpg)", ".jpg");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (.png)", ".png");

     

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                System.out.println(file.length());
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
              
                logimgPath = file.getAbsolutePath();
                 lgfield.setText( logimgPath);
                 System.out.println(logimgPath);
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
                alert.initStyle(StageStyle.UNDECORATED);

            }

        }
    }
    
 @FXML
    private boolean verifPlayername1(KeyEvent event){
    
         int nbNonChar1 = 0;
        for (int i = 1; i < tmfield.getText().trim().length(); i++) {
            char ch = tmfield.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar1++;
            }
        }

        if (nbNonChar1 ==0 && tmfield.getText().trim().length() > 2) {
            labeltm.setText("");

            verifPlayername1 = true;
        } else {
            labeltm.setText("you need only characters and more than three");
            verifPlayername1 = false;

        }
        return verifPlayername1;
    }
    @FXML
       private boolean verifPlayername2(KeyEvent event){
    
         int nbNonChar1 = 0;
        for (int i = 1; i < plfield.getText().trim().length(); i++) {
            char ch = plfield.getText().charAt(i);
            if (!Character.isDigit(ch)) {
                nbNonChar1++;
            }
        }

        if (nbNonChar1 ==0 &&  plfield.getText().trim().length()>=1 ) {
            labelpl.setText("");

            verifPlayername2 = true;
        } else {
            labelpl.setText("you need only numbers ");
            verifPlayername2 = false;

        }
        return verifPlayername2;
    }
    
    
}
