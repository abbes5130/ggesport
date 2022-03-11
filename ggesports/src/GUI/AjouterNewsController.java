/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.actualité;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import services.actualiteCRUD;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterNewsController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TextField Titre;
    @FXML
    private TextField Description;
    @FXML
    private Rectangle retImage;
    @FXML
    private Button attachImage;
    private Image image;

    private File file;

    private FileInputStream fileInput;

    private FileOutputStream fileOutput;

    private byte[] userImage;

    private String imgPath;
    private String logimgPath;
    actualiteCRUD ps = new actualiteCRUD();
    actualité p =new actualité();
    
    @FXML
    private DatePicker date;
         DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    @FXML
    private Rectangle retImage1;
    @FXML
    private Button attachImage1;

           private boolean update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
String k =p.getTitre();
    @FXML
    private void Insert(ActionEvent event) {
         actualité p = new actualité();
        p.setDescription(Description.getText());
        p.setTitre(Titre.getText());
        LocalDate localdate= date.getValue();
        Date dateFromPicker = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(dateFromPicker.getTime());
        p.setDate_creation(sqlDate);
        p.setImg(imgPath);
        p.setImg_bg(logimgPath);
        ps.ajouteractualite2(p);
    }

    @FXML
    private void Affichage(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Listnews.fxml"));
            Parent root = loader.load();
            Titre.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

  
    

    @FXML
    private void attachImageOnAction(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(extFilterjpg, extFilterpng);

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                System.out.println(file.length());
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                retImage.setFill(new ImagePattern(image));
                imgPath = file.getAbsolutePath();
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
    private void attachImageOnAction1(ActionEvent event) throws IOException {
          FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(extFilterjpg, extFilterpng);

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                System.out.println(file.length());
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                retImage1.setFill(new ImagePattern(image));
                logimgPath = file.getAbsolutePath();
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
                alert.initStyle(StageStyle.UNDECORATED);

            }

        }
    }
      void setTextField(int id, String titre, LocalDate toLocalDate, String description) {

        Titre.setText(titre);
        date.setValue(toLocalDate);
        Description.setText(description);

    }
 void setUpdate(boolean b) {
        this.update = b;

    }
    private void btnUpdateOnAction(ActionEvent event) {
        
    }

    @FXML
    private void update(ActionEvent event) {

    }
    
}
