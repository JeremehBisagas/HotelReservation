package main.hotelreservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LobbyController {

    @FXML
    private Label welcomeLabel;

    /**
     * Sets the welcome message for the user.
     *
     * @param username The currently logged-in user's name.
     */
    public void setWelcomeMessage(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }

    /**
     * Handles the "Book a Room" button click event.
     * Navigates to the BookRoom.fxml scene.
     */
    @FXML
    protected void handleBookRoom(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/BookRoom.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Book a Room");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'Book a Room' view.");
        }
    }

    /**
     * Handles the logout button click event.
     * Redirects the user back to the login screen.
     */
    @FXML
    protected void handleLogout(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/Login-view.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the login view.");
        }
    }

    /**
     * Handles the "Hotel Services" button click event.
     */
    @FXML
    protected void handleHotelServices(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/HotelServices.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Services");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'Hotel Services' view.");
        }
    }

    /**
     * Handles the "View Reservations" button click event.
     */
    @FXML
    protected void handleViewReservations(ActionEvent event) {
        try {
            System.out.println("Attempting to load MyReservation.fxml...");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/MyReservation.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - My Reservations");
            stage.show();
            System.out.println("MyReservation.fxml loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'My Reservations' view.");
        }
    }

    /**
     * Handles the "Room Service" button click event.
     */
    @FXML
    protected void handleRoomService(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/RoomService.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Room Service");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'Room Service' view.");
        }
    }
}