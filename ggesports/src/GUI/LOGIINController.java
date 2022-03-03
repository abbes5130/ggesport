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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.UtilisateursServices;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author ridha
 */
public class LOGIINController implements Initializable {

    @FXML
    private TextField tfmail;
    @FXML
    private PasswordField tfpas;
    @FXML
    private Button btnlogin;
    @FXML
    private Label labsignup;
    @FXML
    private Label labforgot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public LOGIINController() {
        cnx = MyConnexion.getInstance().getConncetion();
    }

    Connection cnx;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    double x,y;
    @FXML
    private void logIn(ActionEvent event) throws IOException, Exception {
UtilisateursServices hr = new UtilisateursServices();
        String email = tfmail.getText();
        String password = hr.encrypt(tfpas.getText());

        Users r = new Users();
           Role a = new Role();
        

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "entrez vos données");

        } else {

            String sql = "SELECT * FROM users join role on users.id_role=role.id_role Where email = ? and password = ? ";
            try {
                preparedStatement = cnx.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet rs = preparedStatement.executeQuery();

                //System.out.println(passdecrypted+" "+passdb);
                if (rs.next()) {

                    Users p = new Users(rs.getInt("id_user"), rs.getInt("phone_number"), rs.getInt("id_role"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"));
                    a=new Role(rs.getString("rolename"));
                    p.role=a;
                    Users.current_user = p;
                    
                    /*String passdb = Users.current_user.getPassword();
                    String passdecrypted = hr.decrypt(passdb);
                    System.out.println(Users.current_user.getEmail());*/
                    if (Users.current_user.role.getRolename().equals("Administrateur") || Users.current_user.role.getRolename().equals("Responsables") ) {

                        JOptionPane.showMessageDialog(null, "login");

                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                       
                        Scene scene = new Scene(root);

                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } else {
                        /*Stage krrr = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));

                        Scene scene = new Scene(root);

                        krrr.setScene(scene);
                        krrr.show();*/
                    }

                } else {

                    JOptionPane.showMessageDialog(null, "entrez des données valides");
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

            } catch (Exception ex) {
                Logger.getLogger(LOGIINController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void signup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sign.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void sendemail(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Mail.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    }
    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
}
