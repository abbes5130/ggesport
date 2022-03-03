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
            
            Users u = new Users(i,tfFirstname.getText(),tflastname.getText(),tfemail.getText(),tfpass.getText());
            su.Sign_in(u);
    }}
    }
   
    
