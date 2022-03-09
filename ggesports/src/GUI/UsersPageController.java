/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Role;
import entities.Users;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
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
    @FXML
    private Button btnBlock;
    @FXML
    private Button btnDeBlock;
    @FXML
    private TableColumn<Users, String> colStatus;
    @FXML
    private TextField filterField;
    private final ObservableList<Users> dataList = FXCollections.observableArrayList();
    @FXML
    private Label errnom;
    @FXML
    private Label erremail;
    @FXML
    private Label errprenom;
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
        showBox();
        search();
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
        colStatus.setCellValueFactory(new PropertyValueFactory<Users, String>("check_account"));
       colRoleName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Users, String> param) {
                return new SimpleStringProperty(param.getValue().role.getRolename());
            }
        });
        
        tvUser.setItems(List);
        
    }

    @FXML
    private void selectdl(MouseEvent event) throws Exception {
        UtilisateursServices su= new UtilisateursServices() ;
        Users evt = tvUser.getSelectionModel().getSelectedItem();
        tfNom.setText(evt.getFirstname());
        tfPrenom.setText(evt.getLastname());
        tfEmail.setText(evt.getEmail());
        String pass = evt.getPassword();
        tfPassword.setText(su.decrypt(pass));
        
        
        String a = Integer.toString(evt.getId_user());
        tfID.setText(a);
        String b = Integer.toString(evt.getPhone_number());
        tfNumero.setText(b);
         search();
    }

    @FXML
    private void handleButtonActionajouter(ActionEvent event) {
        Users r = new Users();
            UtilisateursServices su= new UtilisateursServices() ;
            int i = Integer.parseInt(tfNumero.getText());
            int j = Integer.parseInt(tfRole.getText());
            
            Users u = new Users(i,j,tfNom.getText(),tfPrenom.getText(),tfEmail.getText(),tfPassword.getText());
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
            
            else{
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
             search();
        
    }}

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
             search();
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
            
            else{
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
             search();
    }}
    
     @FXML
     private void block (ActionEvent event){
          Users r = new Users();
        UtilisateursServices su= new UtilisateursServices();
            Users u= new Users();
            int id=Integer.parseInt(tfID.getText());
            u.setId_user(id);
            System.out.println(id);
            su.blocker(u);
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
             search();
         
     }
     @FXML
     private void deblock (ActionEvent event){
          Users r = new Users();
        UtilisateursServices su= new UtilisateursServices();
            Users u= new Users();
            int id=Integer.parseInt(tfID.getText());
            u.setId_user(id);
            System.out.println(id);
            su.deblocker(u);
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
            search();
     }
      public void search(){
        Users r = new Users();
         ObservableList<Users> dataList=tvUser.getItems();
          FilteredList<Users> filteredData = new FilteredList<>(dataList, b -> true);
          filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Users -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Users.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Users.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                } else if (Users.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                }else if (Users.role.getRolename().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                         }else if (Users.getCheck_account().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Users.getPhone_number()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Users> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvUser.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvUser.setItems(sortedData);
               
        
    }    
       @FXML
    private boolean testnom(KeyEvent event) {
        
         int nbNonChar = 0;
            for (int i = 1; i < tfNom.getText().trim().length(); i++) {
                char ch = tfNom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfNom.getText().trim().length() >=3) {
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
        if (tfEmail.getText() == null) {
            return false;
        }

        if (pat.matcher(tfEmail.getText()).matches() == false) {
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
        
         String PAS = tfPassword.getText().trim();

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
            for (int i = 1; i < tfPrenom.getText().trim().length(); i++) {
                char ch = tfPrenom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfPrenom.getText().trim().length() >=3) {
            errprenom.setText("Prenom valide");
            
          verificationUserPrenom = true;
            } else {
                errprenom.setText("Il faut au moins 3 caracteres");
                verificationUserPrenom = false;

            }
            return verificationUserPrenom;
    }
}
