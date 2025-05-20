module main.hotelreservation {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.hotelreservation to javafx.fxml;
    exports main.hotelreservation;
}