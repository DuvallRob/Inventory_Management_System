package main;

/**
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Inventory Management program is a software that manages
 * an inventory of parts and products consisting of parts.
 */

/**
 * This class creates the main screen of the application.
 */
public class main extends Application {
    /**
     * This method creates an FXML stage and loads the initial scene.
     *
     * @param stage The primary stage for the application
     * @throws Exception if the FXML file fails to load
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/main-screen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the main method of the application, which is the starting point of the application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
