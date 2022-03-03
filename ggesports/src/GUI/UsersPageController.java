/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Role;
import entities.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import services.UtilisateursServices;


/**
 * FXML Controller class
 *
 * @author ridha
 */
public class UsersPageController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfRole;
    @FXML
    private TableView<Users> tvUser;
    @FXML
    private TableColumn<Users, String> colNom;
    @FXML
    private TableColumn<Users, String> colPrenom;
    @FXML
    private TableColumn<Users, String> colEmail;
    @FXML
    private TableColumn<Users, String> colPassword;
    @FXML
    private TableColumn<Users, Integer> colNumero;
    @FXML
    private TableColumn<Users, Integer> colRole;
    @FXML
    private TableColumn<Users, Integer> colID;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button brnModifier;
    @FXML
    private TextField tfID;
    @FXML
    private TableColumn<Users, String> colRoleName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showBox();
    } 
    public void showBox(){
        Users r = new Users();
        
        UtilisateursServices sp = new UtilisateursServices();
        ObservableList<Users> List = (ObservableList<Users>) sp.find();
        colNom.setCellValueFactory(new PropertyValueFactory<Users,String>("firstname"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Users, String>("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Users, String>("Email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Users, String>("Password"));
        colNumero.setCellValueFactory(new PropertyValueFactory<Users, Integer>("phone_number"));
        colRole.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id_role"));
        colID.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id_user"));
       colRoleName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Users, String> param) {
                return new SimpleStringProperty(param.getValue().role.getRolename());
            }
        });
        
        tvUser.setItems(List);
        
    }

    @FXML
    private void selectdl(MouseEvent event) {
        Users evt = tvUser.getSelectionModel().getSelectedItem();
        tfNom.setText(evt.getFirstname());
        tfPrenom.setText(evt.getLastname());
        tfEmail.setText(evt.getEmail());
        tfPassword.setText(evt.getPassword());
        
        String a = Integer.toString(evt.getId_user());
        tfID.setText(a);
        String b = Integer.toString(evt.getPhone_number());
        tfNumero.setText(b);
    }

    @FXML
    private void handleButtonActionajouter(ActionEvent event) {
        Users r = new Users();
            UtilisateursServices su= new UtilisateursServices() ;
            int i = Integer.parseInt(tfNumero.getText());
            int j = Integer.parseInt(tfRole.getText());
            
            Users u = new Users(i,j,tfNom.getText(),tfPrenom.getText(),tfEmail.getText(),tfPassword.getText());
            su.ajouter(u);
//ServiceUser su = new ServiceUser();
            ObservableList<Users> List = (ObservableList<Users>) su.find();
            colNom.setCellValueFactory(new PropertyValueFactory<Users,String>("firstname"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<Users,String>("lastname"));
            colEmail.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
            colPassword.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
            colNumero.setCellValueFactory(new PropertyValueFactory<Users,Integer>("phone_number"));
            colRole.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id_role"));
            //colRoleName.setCellValueFactory(new PropertyValueFactory<Users, String>(r.role.getRolename()));
            tvUser.setItems(List);  
        
    }

    @FXML
    private void handleButtonActionsupprimer(ActionEvent event) {
        Users r = new Users();
        UtilisateursServices su= new UtilisateursServices();
            Users u= new Users();
            int id=Integer.parseInt(tfID.getText());
            u.setId_user(id);
            System.out.println(id);
            su.supprimer(u);
            ObservableList<Users> List = (ObservableList<Users>) su.find();
            colNom.setCellValueFactory(new PropertyValueFactory<Users,String>("firstname"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<Users,String>("lastname"));
            colEmail.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
            colPassword.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
            colNumero.setCellValueFactory(new PropertyValueFactory<Users,Integer>("phone_number"));
            colRole.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id_role"));
            //colRoleName.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));
           // colRoleName.setCellValueFactory(new PropertyValueFactory<Users, String>(r.role.getRolename()));
            tvUser.setItems(List);
    }

    @FXML
    private void handleButtonActionmodifier(ActionEvent event) {
        Users r = new Users();
        UtilisateursServices tc = new UtilisateursServices();
            Users u = new Users();
            int id = Integer.parseInt(tfID.getText());
            u.setId_user(id);
            u.setFirstname(tfNom.getText());
            u.setLastname(tfPrenom.getText());
            u.setEmail(tfEmail.getText());
            u.setPassword(tfPassword.getText());
           // u.getId_role(tfRole.getText());
            int i =Integer.parseInt(tfNumero.getText());
            u.setPhone_number(i);
            tc.modifier(u);
            ObservableList<Users> List = (ObservableList<Users>) tc.find();
            colNom.setCellValueFactory(new PropertyValueFactory<Users,String>("firstname"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<Users,String>("lastname"));
            colEmail.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
            colPassword.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
            colNumero.setCellValueFactory(new PropertyValueFactory<Users,Integer>("phone_number"));
            colRole.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id_role"));
           // colRoleName.setCellValueFactory(new PropertyValueFactory<Users, String>(r.role.getRolename()));
            tvUser.setItems(List);
    }
    
}
