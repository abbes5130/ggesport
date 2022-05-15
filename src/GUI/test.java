/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class test extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
           Parent root = FXMLLoader.load(getClass().getResource("DashAdmin.fxml"));
           // Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ajouter news.fxml"));
            Scene scene = new Scene(root,800,600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex ) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
     
}
