/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Commentaire;
import entites.actualité;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.commentaireCRUD;
import services.actualiteCRUD;
//import gui.FXMLController;
/**
 * FXML Controller class
 *
 * @author khale
 */
public class actDetailController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button commenter;
    private Label controleSaisie;
    @FXML
    private TextField contenu;
    @FXML
    private TextField ajoutC;
    @FXML
    private GridPane grid;
    
    @FXML
    private Button btnB;
    
    
    private MylistenerOnComment Mylistener;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mylistener = new MylistenerOnComment() {
               
                public void onClickListener(Commentaire c) {
                  FXMLLoader fxmlLoader = new FXMLLoader();
//                    System.out.println(p);
                  try{
                      
                      fxmlLoader.setLocation(getClass().getResource("/gui/commentaireDetail.fxml"));
                      Parent root = fxmlLoader.load();
                      Stage mainStage = new Stage();
                      Scene scene = new Scene(root);
                      mainStage.setScene(scene);
                      CommentaireDetailController fullpostController = fxmlLoader.getController();
                      fullpostController.setData(c);
                      mainStage.show();
                      
                  }catch(Exception e){
                      JOptionPane.showMessageDialog(null, e);
                      
                  }
                }
        };

    }
    //private SPublication spub;    
    private actualité actualité;
    private Commentaire commentaire;
    public void setData(actualité actualite) {
        this.actualité = actualite;
        contenu.setText(actualite.getDescription());
        afficher();
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Modification Pub");
        alert.setHeaderText("Etes vous sure de modifier la publication?");
        
      Optional<ButtonType> option = alert.showAndWait();
      
      if (option.get() == ButtonType.OK) {
        actualiteCRUD ps = new actualiteCRUD();
        actualité.setDescription(contenu.getText());
        ps.modifier(actualité);
      }
      else{
        return;
      }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression actualité");
        alert.setHeaderText("Etes vous sure de supprimer la actualité?");
        
      Optional<ButtonType> option = alert.showAndWait();
      
      if (option.get() == ButtonType.OK) {
        actualiteCRUD ps = new actualiteCRUD();
        ps.deleteactualite2(actualité);
        
        Stage stage = (Stage) supprimer.getScene().getWindow();
        stage.close();
        afficher(); 
      }
      else{
        return;
      }
    }

     @FXML
    private void publierC(ActionEvent event) throws Exception {
        if(ajoutC.getText()!= null && !ajoutC.getText().isEmpty()){
LocalDate localdate= date.getValue();
        Date dateFromPicker = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(dateFromPicker.getTime());       
           Commentaire c = new Commentaire(actualité.getId(),ajoutC.getText(),sqlDate);
           commentaireCRUD sc = new commentaireCRUD();
           sc.ajouter(c);
           afficher(); 

           //controleSaisie.setText("Votre commentaire est ajoutée");
           //controleSaisie.setTextFill(color(0, 255, 0));
           Alert alertAjout = new Alert(Alert.AlertType.INFORMATION);
        
           alertAjout.setTitle("Ajout commentaire");
           alertAjout.setHeaderText(null);
           alertAjout.setContentText("votre commentaire est ajoutée avec succées");
           sendMail("mohamedtaha.mejdoub@esprit.tn");

           alertAjout.showAndWait();
        }
        else{
           controleSaisie.setText("Vous n'avez saisi aucune chose pour commenter  !!!");

           controleSaisie.setTextFill(color(255, 0, 0));  
        }
        
    }
    
    public void afficher(){
        //System.out.println("test");
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        commentaireCRUD ps = new commentaireCRUD();
        List<Commentaire> C = ps.find(actualité.getId());
        System.out.println(C);
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < C.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/commentaire.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CommentaireController CommentaireController = fxmlLoader.getController();
                CommentaireController.setData(C.get(i),Mylistener);
                

               
                row++;
                

                grid.add(anchorPane, column, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
              
               GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10, 10, 10, 10));
              
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    

    @FXML
    private void actualiser(ActionEvent event) {
        afficher();
    }
    
    @FXML
    private void returnToA(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("listnews.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
     
    
    
    @FXML
    private void clear2() {
        ajoutC.setText("");
    }

public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "mohamedtaha.mejdoub@esprit.tn";
        //Your gmail password
        String password = "213JMT0293";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First Email from Java App");
            String htmlCode = "<h1> comment ajouté </h1> <br/> <h2><b>merci </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
    

    

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            