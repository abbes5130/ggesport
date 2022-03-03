/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author ridha
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception  {
        Parent root = FXMLLoader.load(getClass().getResource("Sign.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
       
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
       
        
    }

    
}
