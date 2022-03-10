/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.UtilisateursServices;

/**
 * FXML Controller class
 *
 * @author ridha
 */
public class SignController implements Initializable {

    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpass;
    @FXML
    private TextField tflastname;
    @FXML
    private Button btnsignin;
    @FXML
    private Label lablogin;
    @FXML
    private Label errnom;
    @FXML
    private Label errprenom;
    @FXML
    private Label erremail;
    @FXML
    private Label errpass;
    private boolean verificationUserPrenom;
    private boolean verificationUsernom;
    private boolean verificationUserEmail;
    private boolean verificationUserpasword ;
    private boolean verificationUsernum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       private Stage stage;
       private Scene scene;
       private Parent root;
    @FXML
    private void login(MouseEvent event) throws IOException {
        
                        Parent root = FXMLLoader.load(getClass().getResource("LOGIIN.fxml"));

                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        
}

    

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void register(ActionEvent event) {
        if(tfnum.getText().isEmpty()||tfFirstname.getText().isEmpty()||tflastname.getText().isEmpty()||tfemail.getText().isEmpty()||tfpass.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "entrez vos données");
        }
        else{
        Users r = new Users();
            UtilisateursServices su= new UtilisateursServices() ;
            int i = Integer.parseInt(tfnum.getText());
            //int j = Integer.parseInt(tfRole.getText());
            
            if (verificationUsernom != true ){
            JOptionPane.showMessageDialog(null, "entrez des parametres valides");
            }
            else if(verificationUserPrenom != true){
              JOptionPane.showMessageDialog(null, "entrez des parametres valides");  
            }
             else if(verificationUserEmail != true){
              JOptionPane.showMessageDialog(null, "entrez des parametres valides");  
            }
               else if(verificationUserpasword != true){
              JOptionPane.showMessageDialog(null, "entrez des parametres valides");  
            }
            
            else{Users u = new Users(i,tfFirstname.getText(),tflastname.getText(),tfemail.getText(),tfpass.getText());
            su.Sign_in(u);
    }}
    }
    @FXML
    private boolean testnom(KeyEvent event) {
        
         int nbNonChar = 0;
            for (int i = 1; i < tfFirstname.getText().trim().length(); i++) {
                char ch = tfFirstname.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfFirstname.getText().trim().length() >=3) {
            errnom.setText("Nom valide");
            
             verificationUsernom = true;
            } else {
              errnom.setText("Il faut au moins 3 caracteres");
              verificationUsernom = false;

            }
           return verificationUsernom; 
    }

    @FXML
    private boolean testmail(KeyEvent event) {
         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (tfemail.getText() == null) {
            return false;
        }

        if (pat.matcher(tfemail.getText()).matches() == false) {
             verificationUserEmail = false;
            erremail.setText("Veuillez verifier la forme ***@**.**");
            
//            

        } else {
             erremail.setText("Mail valide");
             verificationUserEmail = true;
        }
        return verificationUserEmail;
    }

    

    @FXML
    private boolean testpassword(KeyEvent event) {
        
         String PAS = tfpass.getText().trim();

        if (PAS.length() >= 6) {
            errpass.setText("Longeur juste");
             verificationUserpasword = true;
        }else{
        verificationUserpasword = false;
            errpass.setText("Utilisez au moins six caractères");
            
        }
        return verificationUserpasword;
    }
    @FXML
    private boolean testprenom(KeyEvent event) {
        int nbNonChar = 0;
            for (int i = 1; i < tflastname.getText().trim().length(); i++) {
                char ch = tflastname.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tflastname.getText().trim().length() >=3) {
            errprenom.setText("Prenom valide");
            
          verificationUserPrenom = true;
            } else {
                errprenom.setText("Il faut au moins 3 caracteres");
                verificationUserPrenom = false;

            }
            return verificationUserPrenom;
    }
    }
   
    
