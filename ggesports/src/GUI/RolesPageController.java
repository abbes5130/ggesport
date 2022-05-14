/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Role;
import entities.Users;
import java.net.URL;
import java.util.List;
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
import services.RoleServices;
import services.UtilisateursServices;

/**
 * FXML Controller class
 *
 * @author ridha
 */
public class RolesPageController implements Initializable {

    @FXML
    private Button brnModifier;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfRole;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableView<Role> tvRole;
    @FXML
    private TableColumn<Role, Integer> colid;
    @FXML
    private TableColumn<Role, String> colnom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showBox();
    }    

    @FXML
    private void handleButtonActionmodifier(ActionEvent event) {
         
        RoleServices tc = new RoleServices();
            Role u = new Role();
            int id = Integer.parseInt(tfID.getText());
            u.setId_role(id);
            u.setRolename(tfRole.getText());
           
            tc.modifierRole(u);
            ObservableList<Role> List = (ObservableList<Role>) tc.findRole();
            colnom.setCellValueFactory(new PropertyValueFactory<Role,String>("rolename"));
            
            tvRole.setItems(List);
    }

    @FXML
    private void handleButtonActionsupprimer(ActionEvent event) {
         
    RoleServices su= new RoleServices();
            Role u= new Role();
            int id=Integer.parseInt(tfID.getText());
            u.setId_role(id);
            System.out.println(id);
            su.supprimerRole(u);
            ObservableList<Role> List = (ObservableList<Role>) su.findRole();
            colnom.setCellValueFactory(new PropertyValueFactory<Role,String>("rolename"));
         
            tvRole.setItems(List);
        
    }

    @FXML
    private void handleButtonActionajouter(ActionEvent event) {
        Role r = new Role();
            RoleServices su= new RoleServices() ;
            
            
            Role u = new Role(tfRole.getText());
            su.ajouterRole(u);
//ServiceUser su = new ServiceUser();
            ObservableList<Role> List = (ObservableList<Role>) su.findRole();
            colnom.setCellValueFactory(new PropertyValueFactory<Role,String>("rolename"));
            tvRole.setItems(List);  
    }

    @FXML
    private void selectdl(MouseEvent event) {
         Role evt = tvRole.getSelectionModel().getSelectedItem();
        tfRole.setText(evt.getRolename());
        
        
        String a = Integer.toString(evt.getId_role());
        tfID.setText(a);
        
    }
     public void showBox(){
        Role r = new Role();
        
        RoleServices sp = new RoleServices();
        ObservableList<Role> List = (ObservableList<Role>) sp.findRole();
        colid.setCellValueFactory(new PropertyValueFactory<Role,Integer>("id_role"));
        colnom.setCellValueFactory(new PropertyValueFactory<Role, String>("rolename"));
        
            
        
        
        tvRole.setItems(List);
        
    }
}
