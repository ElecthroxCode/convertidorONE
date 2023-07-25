
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ConvertidorONE extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocumentPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/img/one2.jpg"));
        stage.setTitle("'Para quien tiene ganas de aprender y prisa por trabajar'");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
