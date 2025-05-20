package main.hotelreservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LaundryServiceController {

    @FXML
    private TextArea laundryDetailsTextArea;

    /**
     * Handles the submission of the laundry service request.
     */
    @FXML
    protected void handleSubmitLaundry(ActionEvent event) {
        String details = laundryDetailsTextArea.getText().trim();

        if (details.isEmpty()) {
            // Show warning alert if no details are provided
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Laundry Request");
            alert.setHeaderText("Missing Details");
            alert.setContentText("Please enter the laundry details before submitting.");
            alert.showAndWait();
            System.out.println("No laundry details provided.");
        } else {
            // Process the laundry request (e.g., save to DB, etc.)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Laundry Request Submitted");
            alert.setHeaderText("Success");
            alert.setContentText("Your laundry request has been submitted successfully.");
            alert.showAndWait();

            System.out.println("Submitting laundry request: " + details);

            // Clear the text area after submission
            laundryDetailsTextArea.clear();
        }
    }

    /**
     * Navigates back to the Hotel Services screen.
     */
    @FXML
    protected void handleBackToServices(ActionEvent event) {
        System.out.println("Navigating back to Hotel Services...");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/HotelServices.fxml"));
            Stage stage = (Stage) laundryDetailsTextArea.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Services");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the Hotel Services view.");
        }
    }
}
