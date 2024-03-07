package com.example.profx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/profx/hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1080, 650);
        primaryStage.setTitle("Hotel Finder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Cord C = new Cord();


    }
}
