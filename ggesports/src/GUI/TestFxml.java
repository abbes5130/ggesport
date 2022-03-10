
package GUI;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


public class TestFxml extends Application {
    
   



    private Stage primaryStage;
    private AnchorPane mainLayout ;
    @Override
    public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("GGEsports");
    showMainView();
        
        
    }

private void showMainView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("main.fxml"));
    mainLayout = loader.load();
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
    
}
    public static void main(String[] args) {
        launch(args);
            

    }
    
    
}
