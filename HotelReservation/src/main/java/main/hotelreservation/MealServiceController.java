package main.hotelreservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import java.io.IOException;
import java.util.*;

public class MealServiceController {

    @FXML
    private TextArea specialInstructionsTextArea;

    @FXML
    private TextArea orderSummaryTextArea;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Spinner<Integer> qtyPancakes, qtyOmelette, qtyCaesarSalad, qtyClubSandwich, qtyGrilledSalmon, qtySteak;

    @FXML
    private Spinner<Integer> qtyWater, qtyWine, qtyTea, qtyCoffee;

    private final Map<String, Double> prices = new LinkedHashMap<>() {{
        put("Pancakes", 85.99);
        put("Omelette", 109.49);
        put("Caesar Salad", 130.49);
        put("Club Sandwich", 55.99);
        put("Grilled Salmon", 280.99);
        put("Steak", 310.99);
        put("Water", 25.00);
        put("Wine", 199.99);
        put("Tea", 45.00);
        put("Coffee", 60.00);
    }};

    private final Map<String, Integer> selectedMeals = new LinkedHashMap<>();

    private void addMeal(String name, Spinner<Integer> qtySpinner) {
        int qty = qtySpinner.getValue();
        if (qty <= 0) return;

        selectedMeals.put(name, selectedMeals.getOrDefault(name, 0) + qty);
        updateOrderSummary();
    }

    private void updateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        double total = 0;

        for (Map.Entry<String, Integer> entry : selectedMeals.entrySet()) {
            String meal = entry.getKey();
            int qty = entry.getValue();
            double price = prices.get(meal);
            summary.append(String.format("%s x%d - ₱%.2f%n", meal, qty, price * qty));
            total += price * qty;
        }

        if (specialInstructionsTextArea.getText().trim().length() > 0) {
            summary.append("\nSpecial Instructions:\n");
            summary.append(specialInstructionsTextArea.getText().trim()).append("\n");
        }

        orderSummaryTextArea.setText(summary.toString());
        totalPriceLabel.setText(String.format("Total: ₱%.2f", total));
    }

    // Food handlers
    @FXML protected void handleOrderPancakes() { addMeal("Pancakes", qtyPancakes); }
    @FXML protected void handleOrderOmelette() { addMeal("Omelette", qtyOmelette); }
    @FXML protected void handleOrderCaesarSalad() { addMeal("Caesar Salad", qtyCaesarSalad); }
    @FXML protected void handleOrderClubSandwich() { addMeal("Club Sandwich", qtyClubSandwich); }
    @FXML protected void handleOrderGrilledSalmon() { addMeal("Grilled Salmon", qtyGrilledSalmon); }
    @FXML protected void handleOrderSteak() { addMeal("Steak", qtySteak); }

    // Drink handlers
    @FXML protected void handleOrderWater() { addMeal("Water", qtyWater); }
    @FXML protected void handleOrderWine() { addMeal("Wine", qtyWine); }
    @FXML protected void handleOrderTea() { addMeal("Tea", qtyTea); }
    @FXML protected void handleOrderCoffee() { addMeal("Coffee", qtyCoffee); }

    @FXML
    protected void handlePlaceOrder(ActionEvent event) {
        if (selectedMeals.isEmpty()) {
            orderSummaryTextArea.setText("Please select at least one item before placing the order.");
            totalPriceLabel.setText("Total: ₱0.00");
            return;
        }

        double total = selectedMeals.entrySet().stream()
                .mapToDouble(entry -> prices.get(entry.getKey()) * entry.getValue())
                .sum();

        orderSummaryTextArea.appendText("\n\nOrder successfully placed. Thank you!");
        totalPriceLabel.setText(String.format("Total: ₱%.2f", total));
        selectedMeals.clear();
        specialInstructionsTextArea.clear();
    }

    @FXML
    protected void handleReviewOrder(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Review Order");

        VBox dialogVbox = new VBox(10);
        dialogVbox.setPadding(new Insets(15));

        TextArea reviewArea = new TextArea();
        reviewArea.setEditable(false);
        reviewArea.setWrapText(true);

        StringBuilder reviewText = new StringBuilder();
        double total = 0;

        if (selectedMeals.isEmpty() && specialInstructionsTextArea.getText().trim().isEmpty()) {
            reviewText.append("No items selected and no special instructions provided.");
        } else {
            for (Map.Entry<String, Integer> entry : selectedMeals.entrySet()) {
                String meal = entry.getKey();
                int qty = entry.getValue();
                double price = prices.get(meal);
                reviewText.append(String.format("%s x%d - ₱%.2f%n", meal, qty, price * qty));
                total += price * qty;
            }

            if (!specialInstructionsTextArea.getText().trim().isEmpty()) {
                reviewText.append("\nSpecial Instructions:\n");
                reviewText.append(specialInstructionsTextArea.getText().trim()).append("\n");
            }

            reviewText.append(String.format("%nTotal: ₱%.2f", total));
        }

        reviewArea.setText(reviewText.toString());

        Button btnOk = new Button("OK");
        btnOk.setOnAction(e -> {
            if (selectedMeals.isEmpty() && !specialInstructionsTextArea.getText().trim().isEmpty()) {
                specialInstructionsTextArea.clear();
                updateOrderSummary();
            }
            dialog.close();
        });

        dialogVbox.getChildren().addAll(reviewArea, btnOk);

        Scene dialogScene = new Scene(dialogVbox, 400, 300);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    @FXML
    protected void handleBackToServices(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/HotelServices.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Services");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
