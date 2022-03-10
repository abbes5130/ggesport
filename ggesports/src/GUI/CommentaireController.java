/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import entities.actualité;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author khale
 */
public class CommentaireController implements Initializable {

    @FXML
    private Label contenu;
    @FXML
    private Label date;
    @FXML
    private Label id_actuali;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    private Commentaire commentaire;
    private MylistenerOnComment Mylistener;
    public void setData(Commentaire pub, MylistenerOnComment Mylistener) {
        this.Mylistener = Mylistener;
        this.commentaire = pub;
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dob = format.format(pub.getDate_commentaire());
        contenu.setText(pub.gettexte());
       // date.setText(pub.getCreated_at());
       date.setText(dob);
    }

    @FXML
    private void click(javafx.scene.input.MouseEvent event) {
        Mylistener.onClickListener(commentaire);
    }
}
