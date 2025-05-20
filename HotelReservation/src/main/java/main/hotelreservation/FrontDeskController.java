package main.hotelreservation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class FrontDeskController {

    @FXML private Label reservationStatus;
    @FXML private Label checkInStatus;
    @FXML private Label pendingRequestsStatus;

    @FXML private TextArea billingInfoArea;
    @FXML private TextArea specialRequestsArea;
    @FXML private TextArea chatArea;
    @FXML private TextField chatInput;

    @FXML
    public void initialize() {
        if (reservationStatus == null || chatArea == null) {
            System.out.println("Error: FXML elements for FrontDeskController not fully initialized.");
        }
    }

    @FXML
    private void handleCheckIn() {
        checkInStatus.setText("Checked In ✅");
        appendChatMessage("System: You have successfully checked in.");
    }

    @FXML
    private void handleModifyReservation() {
        appendChatMessage("System: Modify Reservation clicked.");
        // Open modify reservation dialog or scene here
    }

    @FXML
    private void handleRequestAssistance() {
        appendChatMessage("System: Assistance requested.");
        pendingRequestsStatus.setText("Assistance requested 📩");
    }

    @FXML
    private void handleUpdateBilling() {
        billingInfoArea.setText("Billing updated at " + java.time.LocalTime.now());
    }

    @FXML
    private void handleSubmitSpecialRequest() {
        String request = specialRequestsArea.getText();
        if (request.isBlank()) {
            appendChatMessage("System: Special request cannot be empty.");
        } else {
            appendChatMessage("Special Request submitted: " + request);
            specialRequestsArea.clear();
            pendingRequestsStatus.setText("Special Request pending 📩");
        }
    }

    @FXML
    private void handleSendMessage() {
        String msg = chatInput.getText();
        if (msg != null && !msg.isBlank()) {
            appendChatMessage("You: " + msg);
            chatInput.clear();
        }
    }

    private void appendChatMessage(String message) {
        chatArea.appendText(message + "\n");
    }

    @FXML
    private void handleBackToServices() {
        try {
            Stage stage = (Stage) reservationStatus.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/hotelreservation/HotelServices.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Hotel Services");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
