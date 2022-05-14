/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.News;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.NewsCRUD;
/**
 * FXML Controller class
 *
 * @author khale
 */
public class ActualiteController implements Initializable {

    @FXML
    private Label contenu;
    @FXML
    private Label date;
    
    @FXML
    private Label nbComs;
    private Label titre;
    @FXML
    private ImageView logo;
    @FXML
    private Label v;
    @FXML
    private ImageView img_bg;
   

    /**
     * Initializes the controller class.
     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    private NewsCRUD spub;
    private News actualite;
    private MylistenerOnPub mylistener;
    
    
    public void setData(News a,NewsCRUD sa, MylistenerOnPub Mylistener) {
        this.mylistener = Mylistener;
        this.actualite = a;
        contenu.setText(a.getDescription());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dob = format.format(a.getDate_creation());
        date.setText(dob);
        v.setText(a.getTitre());
File imageFile1 = new File(a.getImg());
File imageFile2 = new File(a.getImg_bg());
Image image1 = new Image(imageFile2.toURI().toString());         

Image image = new Image(imageFile1.toURI().toString());         
logo.setImage(image);
 img_bg.setImage(image1);     
        
        nbComs.setText(String.valueOf(sa.nombreCommentaires(a)));
    }
    
 

    @FXML
    private void click(javafx.scene.input.MouseEvent event) {
        mylistener.onClickListener(actualite);
    }

   
    
    }
 
        
    

