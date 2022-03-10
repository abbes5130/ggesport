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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.UtilisateursServices;

/**
 * FXML Controller class
 *
 * @author ridha
 */
public class SettingsController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private Button btnupdate;
    @FXML
    private PasswordField tfpass;
    @FXML
    private Label errnom;
    @FXML
    private Label errprenom;
    @FXML
    private Label errpass;
    @FXML
    private Label errmail;
    @FXML
    private TextField tfid;
    @FXML
    private Label labnom;
    @FXML
    private Label labprenom;
    private boolean verificationUserPrenom;
    private boolean verificationUsernom;
    private boolean verificationUserEmail;
    private boolean verificationUserpasword ;
    private boolean verificationUsernum;
 private Stage stage;
       private Scene scene;
       private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateursServices hr = new UtilisateursServices();
        String a = Integer.toString(Users.current_user.getId_user());
        tfid.setText(a);
        tfnom.setText(Users.current_user.getFirstname());
        tfprenom.setText(Users.current_user.getLastname());
        tfemail.setText(Users.current_user.getEmail());
        String pass = Users.current_user.getPassword();
        
        try {
            tfpass.setText(hr.decrypt(pass));
        } catch (Exception ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        labnom.setText(Users.current_user.getFirstname());
        labprenom.setText(Users.current_user.getLastname());
    }    

    @FXML
    private void modif(ActionEvent event) throws IOException {
        labnom.setText(Users.current_user.getFirstname());
        labprenom.setText(Users.current_user.getLastname());
        Users r = new Users();
        UtilisateursServices tc = new UtilisateursServices();
            Users u = new Users();
            int id = Integer.parseInt(tfid.getText());
            u.setId_user(id);
            u.setFirstname(tfnom.getText());
            u.setLastname(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setPassword(tfpass.getText());
           // u.getId_role(tfRole.getText());
            /*int i =Integer.parseInt(tfNumero.getText());
            u.setPhone_number(i);*/
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
            tc.modifier(u);
            JOptionPane.showMessageDialog(null, "Redirection Login");
            JOptionPane.showMessageDialog(null, "login");
                        Parent root = FXMLLoader.load(getClass().getResource("DashAdmin.fxml"));  
                        Stage stage = new Stage();
                        stage.close();
            
        Parent roote = FXMLLoader.load(getClass().getResource("LOGIIN.fxml"));

                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(roote);
                        stage.setScene(scene);
                        stage.show();
                        
    }
    @FXML
    private boolean testnom(KeyEvent event) {
        
         int nbNonChar = 0;
            for (int i = 1; i < tfnom.getText().trim().length(); i++) {
                char ch = tfnom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfnom.getText().trim().length() >=3) {
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
            errmail.setText("Veuillez verifier la forme ***@**.**");
            
//            

        } else {
             errmail.setText("Mail valide");
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
            for (int i = 1; i < tfprenom.getText().trim().length(); i++) {
                char ch = tfprenom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfprenom.getText().trim().length() >=3) {
            errprenom.setText("Prenom valide");
            
          verificationUserPrenom = true;
            } else {
                errprenom.setText("Il faut au moins 3 caracteres");
                verificationUserPrenom = false;

            }
            return verificationUserPrenom;
    }
}
