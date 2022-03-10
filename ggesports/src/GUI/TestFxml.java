
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class TestFxml extends Application {
    
   
    @Override
    public void start(Stage primaryStage)  {
        System.out.println("hello");
        
        try{
            URL resource = getClass().getResource("main.fxml");
                      System.out.println("resource is ");
            Parent root = FXMLLoader.load(resource);
                                System.out.println("Load finished");

            Scene scene = new Scene(root , 800 ,600);
            primaryStage.setScene(scene);
            primaryStage.show();
        
        }catch(IOException e){
            e.printStackTrace();
        System.out.println("error happened");
        System.out.println(e.getMessage());
        
        }
        
    }

    public static void main(String[] args) {
        launch(args);


    }
    
}
