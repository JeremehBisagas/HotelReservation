package main.hotelreservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookRoomController {

    @FXML
    private ChoiceBox<String> roomTypeChoiceBox;

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private Label statusLabel;

    @FXML
    private ImageView roomImageView;

    @FXML
    public void initialize() {
        roomTypeChoiceBox.getItems().addAll("Single - ₱500", "Double - ₱800", "Suite - ₱1500");

        roomTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            updateRoomImage(newVal);
        });

        checkOutDatePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null && checkInDatePicker.getValue() != null && !newDate.isAfter(checkInDatePicker.getValue())) {
                statusLabel.setText("Check-out date must be after check-in date.");
                statusLabel.setStyle("-fx-text-fill: red;");
            } else {
                statusLabel.setText("");
            }
        });
    }

    private void updateRoomImage(String roomType) {
        String imagePath = "/images/room-default.jpg";
        if (roomType != null) {
            if (roomType.contains("Single")) {
                imagePath = "/images/sng_600_001.jpg";
            } else if (roomType.contains("Double")) {
                imagePath = "/images/room-double.jpg";
            } else if (roomType.contains("Suite")) {
                imagePath = "/images/room-suite.jpg";
            }
        }

        try (InputStream is = getClass().getResourceAsStream(imagePath)) {
            if (is != null) {
                roomImageView.setImage(new Image(is));
            } else {
                System.out.println("Image not found at: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + imagePath);
        }
    }

    @FXML
    protected void handleConfirmBooking(ActionEvent event) {
        String room = roomTypeChoiceBox.getValue();
        LocalDate checkIn = checkInDatePicker.getValue();
        LocalDate checkOut = checkOutDatePicker.getValue();

        if (room == null || checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            statusLabel.setText("Please select a room and valid date range.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        int pricePerNight = 0;
        if (room.contains("₱500")) pricePerNight = 500;
        else if (room.contains("₱800")) pricePerNight = 800;
        else if (room.contains("₱1500")) pricePerNight = 1500;

        int totalPrice = (int) nights * pricePerNight;

        statusLabel.setText("Room Booked: " + room +
                "\nCheck-in: " + checkIn +
                "\nCheckout: " + checkOut +
                "\nNights: " + nights +
                "\nTotal: ₱" + totalPrice);
        statusLabel.setStyle("-fx-text-fill: green;");

        // Add the reservation to the list in MyReservationsController
        MyReservationsController.addReservation(new Reservation(room, checkIn.toString(), checkOut.toString(), (int) nights, totalPrice));
    }

    @FXML
    protected void handleCancel(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/lobby.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setMaximized(true); // Ensure maximized window
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Lobby");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the lobby view.");
        }
    }

    @FXML
    protected void handleBackToLobby(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/lobby.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600); // Adjust dimensions as needed
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Lobby");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'Lobby' view.");
        }
    }
}