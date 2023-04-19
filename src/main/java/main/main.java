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
 *
 * The Inventory Management program is a software that manages
 * an inventory of parts and products consisting of parts.
 * 
 * 
 */

/**Class that creates main screen of app*/
public class main extends Application {
    /**
     *
     * The start method that creates FXML stage and loads initial scene.
     * 
     * @param stage 
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/main-screen.fxml"));//Location of Main Screen FXML document
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method is starting point of application.
     * 
     * Main method launches application.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
}
