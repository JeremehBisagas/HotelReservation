package main.hotelreservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the combined Login/Register view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Optional UI setup
        stage.setMaximized(true);
        stage.setFullScreen(false); // Set to true if you want borderless full screen
        stage.setTitle("Hotel Del Luna - Authentication");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
