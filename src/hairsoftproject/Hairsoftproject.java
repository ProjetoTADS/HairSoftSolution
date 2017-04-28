package hairsoftproject;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Eduardo
 */
public class Hairsoftproject extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Software HairSoft LTDA");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
