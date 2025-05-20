package main.hotelreservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MyReservationsController { // Changed to public

    @FXML
    private TableView<Reservation> reservationsTable;

    @FXML
    private TableColumn<Reservation, String> roomColumn;

    @FXML
    private TableColumn<Reservation, String> checkInColumn;

    @FXML
    private TableColumn<Reservation, String> checkOutColumn;

    @FXML
    private TableColumn<Reservation, Integer> nightsColumn;

    @FXML
    private TableColumn<Reservation, Integer> totalColumn;

    private static final ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        roomColumn.setCellValueFactory(data -> data.getValue().roomTypeProperty());
        checkInColumn.setCellValueFactory(data -> data.getValue().checkInDateProperty());
        checkOutColumn.setCellValueFactory(data -> data.getValue().checkOutDateProperty());
        nightsColumn.setCellValueFactory(data -> data.getValue().nightsProperty().asObject());
        totalColumn.setCellValueFactory(data -> data.getValue().totalPriceProperty().asObject());

        reservationsTable.setItems(reservations);
    }

    @FXML
    protected void handleBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/lobby.fxml"));
            Stage stage = (Stage) reservationsTable.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Lobby");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the lobby view.");
        }
    }

    @FXML
    protected void handleCancelReservation(ActionEvent event) {
        Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {
            reservations.remove(selectedReservation);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a reservation to cancel.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}