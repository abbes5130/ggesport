
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TestFxml extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));     
            Scene scene = new Scene(root, 1920, 1080);
            primaryStage.setTitle("GGesport");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
