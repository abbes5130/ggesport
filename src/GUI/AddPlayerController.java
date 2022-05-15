/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Player;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.GameCRUD;
import utils.db;

/**
 * FXML Controller class
 *
 * @author mohamedabbes
 */
public class AddPlayerController {
    private File file;

    private FileInputStream fileInput;

    private FileOutputStream fileOutput;

    private byte[] userImage;

    private String imgPath;
    private String logimgPath;
    @FXML
    private Label label_description;
    @FXML
    private Label label_tag;
    @FXML
    private Button retImage1;
    @FXML
    private Label label_photo;
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
                 dsfield_upa.setText( logimgPath);
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

    
    
    
    
    
    
    
 private ObservableList<Player> appMainObservableList_upa;
    @FXML
     private AnchorPane ptpadd;
    @FXML
    private TextField tmfield_upa;
    @FXML
    private TextField tmfield1_upa;
    @FXML
    private TextField lgfield_upa;
    @FXML
    private TextField plfield_upa;
    @FXML
    private TextField dsfield_upa;
    
    @FXML
    private Label label_firstname;
    @FXML
    private Label label_lastname;

 
  private boolean verifPlayername;
   private boolean verifPlayername1;

    
       //fill table button click event
    @FXML
    void fillTable_player(ActionEvent event) {

         Player t = new Player();
        String sql = "INSERT INTO player (id_team, firstname, lastname, description, player_tag, photo) VALUES (?, ?, ?, ?, ?, ?)";
 
PreparedStatement statement;
        try {
            statement = (PreparedStatement) new db().getCnx().prepareStatement(sql);
statement.setInt(1,1);
            statement.setString(2,tmfield_upa.getText());
            
statement.setString(3,tmfield1_upa.getText());
statement.setString(4, lgfield_upa.getText());
statement.setString(5,plfield_upa.getText());
statement.setString(6, dsfield_upa.getText());
       statement.executeUpdate();
       System.out.println("created new player");
            Notifications notifBuilder = Notifications.create().title("alert").text("There is a new player joined the league !").graphic(null).hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_RIGHT);
            notifBuilder.darkStyle();
            notifBuilder.show();
            
        } catch (SQLException ex) {
            Logger.getLogger(GameCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    
    }

    public void setAppMainObservableList(ObservableList<Player> tvObservableList) {
        this.appMainObservableList_upa = tvObservableList;

    }
    @FXML
    private boolean verifPlayername(KeyEvent event){
    
         int nbNonChar = 0;
        for (int i = 1; i < tmfield_upa.getText().trim().length(); i++) {
            char ch = tmfield_upa.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar ==0 && tmfield_upa.getText().trim().length() > 2) {
            label_firstname.setText("");

            verifPlayername = true;
        } else {
            label_firstname.setText("you need only characters and more than three");
            verifPlayername = false;

        }
        return verifPlayername;
    }
       @FXML
    private boolean verifPlayername1(KeyEvent event){
    
         int nbNonChar1 = 0;
        for (int i = 1; i < tmfield1_upa.getText().trim().length(); i++) {
            char ch = tmfield1_upa.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar1++;
            }
        }

        if (nbNonChar1 ==0 && tmfield1_upa.getText().trim().length() > 2) {
            label_lastname.setText("");

            verifPlayername1 = true;
        } else {
            label_lastname.setText("you need only characters and more than three");
            verifPlayername1 = false;

        }
        return verifPlayername1;
    }

    
    
}
