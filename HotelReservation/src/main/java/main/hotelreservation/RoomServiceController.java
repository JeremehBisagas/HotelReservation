package main.hotelreservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomServiceController {

    @FXML
    private TextArea roomServiceRequest;

    @FXML
    protected void handleSubmitRoomService(ActionEvent event) {
        String request = roomServiceRequest.getText();
        if (request == null || request.trim().isEmpty()) {
            showAlert(AlertType.WARNING, "Submission Failed", "Please enter a room service request.");
        } else {
            // Simulate backend handling
            System.out.println("Room service request submitted: " + request);
            showAlert(AlertType.INFORMATION, "Request Submitted", "Your room service request has been sent.");
            roomServiceRequest.clear(); // Clear input after submission
        }
    }

    @FXML
    protected void handleBackToServices(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/HotelServices.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Services");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Navigation Error", "Unable to load the Hotel Services screen.");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
