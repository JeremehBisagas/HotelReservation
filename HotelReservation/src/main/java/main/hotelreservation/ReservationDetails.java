package main.hotelreservation;

import javafx.beans.property.*;

class Reservation {
    private final StringProperty roomType;
    private final StringProperty checkInDate;
    private final StringProperty checkOutDate;
    private final IntegerProperty nights;
    private final IntegerProperty totalPrice;

    public Reservation(String roomType, String checkInDate, String checkOutDate, int nights, int totalPrice) {
        this.roomType = new SimpleStringProperty(roomType);
        this.checkInDate = new SimpleStringProperty(checkInDate);
        this.checkOutDate = new SimpleStringProperty(checkOutDate);
        this.nights = new SimpleIntegerProperty(nights);
        this.totalPrice = new SimpleIntegerProperty(totalPrice);
    }

    public String getRoomType() {
        return roomType.get();
    }

    public StringProperty roomTypeProperty() {
        return roomType;
    }

    public String getCheckInDate() {
        return checkInDate.get();
    }

    public StringProperty checkInDateProperty() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate.get();
    }

    public StringProperty checkOutDateProperty() {
        return checkOutDate;
    }

    public int getNights() {
        return nights.get();
    }

    public IntegerProperty nightsProperty() {
        return nights;
    }

    public int getTotalPrice() {
        return totalPrice.get();
    }

    public IntegerProperty totalPriceProperty() {
        return totalPrice;
    }
}
