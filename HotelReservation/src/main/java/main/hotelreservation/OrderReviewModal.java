// OrderReviewModal.java
package main.hotelreservation;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class OrderReviewModal {

    public static void display(String specialInstructions, List<String> orderItems, double totalPrice) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Order Review");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label titleLabel = new Label("Order Details");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Order items list
        StringBuilder itemListBuilder = new StringBuilder();
        for (String item : orderItems) {
            itemListBuilder.append(item).append("\n");
        }
        TextArea orderList = new TextArea(itemListBuilder.toString());
        orderList.setEditable(false);
        orderList.setWrapText(true);
        orderList.setPrefHeight(200);

        // Special instructions
        Label instructionLabel = new Label("Special Instructions:");
        TextArea instructions = new TextArea(specialInstructions);
        instructions.setEditable(false);
        instructions.setWrapText(true);
        instructions.setPrefHeight(100);

        // Total price
        Label totalLabel = new Label("Total Price: ₱" + String.format("%.2f", totalPrice));
        totalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        layout.getChildren().addAll(titleLabel, orderList, instructionLabel, instructions, totalLabel, closeButton);

        Scene scene = new Scene(layout, 400, 450);
        window.setScene(scene);
        window.showAndWait();
    }
}
