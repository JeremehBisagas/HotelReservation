package main.hotelreservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelServicesController {

    @FXML
    protected void handleLaundryService(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/LaundryService.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Laundry Service");
            stage.show();
            System.out.println("Laundry Service Selected: Redirecting to details...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'Laundry Service' view.");
        }
    }

    @FXML
    protected void handleRoomCleaning(ActionEvent event) {
        try {
            System.out.println("Room Cleaning Service Selected: Redirecting to request form...");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/RoomCleaning.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Room Cleaning");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the Room Cleaning view.");
        }
    }

    @FXML
    protected void handleMealService(ActionEvent event) {
        try {
            System.out.println("Meal Service Selected: Redirecting to Meal Service screen...");
            String mealServicePath = "/main/hotelreservation/MealService.fxml";
            System.out.println("FXML path for Meal Service: " + getClass().getResource(mealServicePath));

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(mealServicePath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Meal Service");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the Meal Service view.");
        }
    }

    // Front Desk handler with ActionEvent parameter
    @FXML
    private void handleFrontDesk(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/hotelreservation/FrontDesk.fxml"));
            System.out.println("FXML Path: " + getClass().getResource("/main/hotelreservation/FrontDesk.fxml"));
            if (getClass().getResource("/main/hotelreservation/FrontDesk.fxml") == null) {
                System.out.println("Error: FrontDesk.fxml does not exist in the resource folder.");
                return;
            }
            Scene scene = new Scene(loader.load(), 900, 600); // Adjust size if needed
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Front Desk");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'Front Desk' view: " + e.getMessage());
        }
    }

    @FXML
    protected void handleBackToLobby(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/Lobby.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
            stage.setTitle("Hotel Del Luna - Lobby");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the 'Lobby' view.");
        }
    }
}
