/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import entities.Comments;
import entities.News;
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
import services.NewsCRUD;
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
               
                public void onClickListener(Comments c) {
                  FXMLLoader fxmlLoader = new FXMLLoader();
//                    System.out.println(p);
                  try{
                      
                      fxmlLoader.setLocation(getClass().getResource("commentaireDetail.fxml"));
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
    private News actualité;
    private Comments commentaire;
    public void setData(News actualite) {
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
        NewsCRUD ps = new NewsCRUD();
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
        NewsCRUD ps = new NewsCRUD();
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
           Comments c = new Comments(actualité.getId(),ajoutC.getText(),sqlDate);
           commentaireCRUD sc = new commentaireCRUD();
           sc.ajouter(c);
           afficher(); 

           //controleSaisie.setText("Votre commentaire est ajoutée");
           //controleSaisie.setTextFill(color(0, 255, 0));
           Alert alertAjout = new Alert(Alert.AlertType.INFORMATION);
        
           alertAjout.setTitle("Ajout commentaire");
           alertAjout.setHeaderText(null);
           alertAjout.setContentText("votre commentaire est ajoutée avec succées");
           SendSMS();

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
        List<Comments> C = ps.find(actualité.getId());
        System.out.println(C);
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < C.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("commentaire.fxml"));
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

public static void SendSMS() throws Exception {
VonageClient client = VonageClient.builder().apiKey("fe6595c0").apiSecret("yXNQOoF73SfQ5A4a").build();       
TextMessage message = new TextMessage("Vonage APIs",
        "21621254238",
        "comment is added "
);

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
      }
    
}
    

    

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            