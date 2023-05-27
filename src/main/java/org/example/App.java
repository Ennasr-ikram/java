package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.entities.Boutique;
import org.example.entities.User;
import org.example.services.BoutiqueService;
import org.example.services.UserService;

import java.security.NoSuchAlgorithmException;

/**
 * Hello world!
 *
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/org.example/Boutique.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public static void main( String[] args ) {
        launch();
    }


}
