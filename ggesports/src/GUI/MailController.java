/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Role;
import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.UtilisateursServices;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author ridha
 */
public class MailController implements Initializable {

    @FXML
    private Button btnlogin;
    @FXML
    private Label labsignup;
    @FXML
    private Label labforgot;
    @FXML
    private TextField tfmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx = MyConnexion.getInstance().getConncetion();
    }    


    

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
    Connection cnx;
    private void sendmail(ActionEvent event) throws Exception {
       
      
}
   public static void Sendmail(String recepient) throws Exception{
        
        System.out.println("prepare sending");
        
        Properties props = new Properties();
        
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        
        String MyAccountEmail ="espritggesport@gmail.com";
        String password = "GGesport1";
        
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyAccountEmail, password);
            }
            

            
        });
        Message message = prepareMessage(session,MyAccountEmail,recepient);
        Transport.send(message);
        System.out.println("mail sent");
        
        
    } 
    private static Message prepareMessage(Session session , String MyAccountEmail,String recepient) throws Exception {
       try {
          
           Users p = new Users();
           UtilisateursServices su = new UtilisateursServices();
        Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(MyAccountEmail));
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
         message.setSubject("Password Forgotten");
         message.setText("Voici Votre Password : \n"+su.decrypt(Users.findpass.getPassword()));
         return message;
         
         
         
         
        } catch (MessagingException ex) {
            Logger.getLogger(UtilisateursServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private void findmail() throws SQLException{
        
        
                    
                    
                   
                   
                    
    
}

    @FXML
    private void send(ActionEvent event) throws SQLException, Exception {
        String email = tfmail.getText();
        UtilisateursServices hr = new UtilisateursServices();
        
       
        Users r = new Users();
           
        

        

            String sql = "SELECT * FROM users where email=?";
            
                preparedStatement = cnx.prepareStatement(sql);
                preparedStatement.setString(1, email);
                
                

                ResultSet rs = preparedStatement.executeQuery();

                
                while(rs.next()) {

                    Users p = new Users(rs.getInt("id_user"), rs.getInt("phone_number"), rs.getInt("id_role"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"),rs.getString("check_account"));
                    Users.findpass=p;
                    
                    Sendmail(email);
                    
                }
                
             JOptionPane.showMessageDialog(null, "Mail Sent");
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void signup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LOGIIN.fxml"));

                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
        
    }

          
    }
    
    

