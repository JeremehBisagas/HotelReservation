package main.hotelreservation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginController {

    // Login tab fields
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorMessage;

    // Register tab fields
    @FXML private TextField registerUsernameField;
    @FXML private TextField mobileField;
    @FXML private TextField emailField;
    @FXML private TextField fullNameField;
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField registerConfirmPasswordField;
    @FXML private Label registerMessage;

    // User data model: username -> User object
    private static final Map<String, User> userAccounts = new HashMap<>();

    // ==== LOGIN ====

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Please enter both username and password.");
            return;
        }

        if (authenticateUser(username, password)) {
            errorMessage.setText("");
            loadLobbyScene(username);
        } else {
            errorMessage.setText("Invalid username or password.");
        }
    }

    @FXML
    protected void onRegisterButtonClick() {
        errorMessage.setText("Please switch to the 'Register' tab.");
    }

    // ==== REGISTER ====

    @FXML
    protected void onRegisterSubmitClick() {
        String username = registerUsernameField.getText().trim();
        String mobile = mobileField.getText().trim();
        String email = emailField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String password = registerPasswordField.getText().trim();
        String confirmPassword = registerConfirmPasswordField.getText().trim();

        // Basic empty checks
        if (username.isEmpty() || mobile.isEmpty() || email.isEmpty() || fullName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            registerMessage.setStyle("-fx-text-fill: red;");
            registerMessage.setText("Please fill in all fields.");
            return;
        }

        // Username uniqueness
        if (userAccounts.containsKey(username)) {
            registerMessage.setStyle("-fx-text-fill: red;");
            registerMessage.setText("Username already exists.");
            return;
        }

        // Mobile number validation: must be 11 digits starting with 09
        if (!mobile.matches("^09\\d{9}$")) {
            registerMessage.setStyle("-fx-text-fill: red;");
            registerMessage.setText("Invalid mobile number. Format: 09XXXXXXXXX");
            return;
        }

        // Email format validation (simple regex)
        if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$", email)) {
            registerMessage.setStyle("-fx-text-fill: red;");
            registerMessage.setText("Invalid email format.");
            return;
        }

        // Password confirmation
        if (!password.equals(confirmPassword)) {
            registerMessage.setStyle("-fx-text-fill: red;");
            registerMessage.setText("Passwords do not match.");
            return;
        }

        // Save new user
        User newUser = new User(username, password, mobile, email, fullName);
        userAccounts.put(username, newUser);

        registerMessage.setStyle("-fx-text-fill: green;");
        registerMessage.setText("Registration successful! You can now log in.");
    }

    @FXML
    protected void onBackToLoginClick() {
        registerMessage.setText("");
    }

    // ==== HELPERS ====

    private boolean authenticateUser(String username, String password) {
        if (!userAccounts.containsKey(username)) return false;
        User user = userAccounts.get(username);
        return user.getPassword().equals(password);
    }

    private void loadLobbyScene(String username) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/hotelreservation/lobby.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);

            LobbyController lobbyController = fxmlLoader.getController();
            lobbyController.setWelcomeMessage(username);

            stage.setScene(scene);
            stage.setTitle("Hotel Del Luna - Lobby");
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("Failed to load the Lobby view: " + e.getMessage());
        }
    }

    // Simple User class to hold all info
    private static class User {
        private final String username;
        private final String password;
        private final String mobile;
        private final String email;
        private final String fullName;

        public User(String username, String password, String mobile, String email, String fullName) {
            this.username = username;
            this.password = password;
            this.mobile = mobile;
            this.email = email;
            this.fullName = fullName;
        }

        public String getPassword() {
            return password;
        }
        // getters if needed...
    }
}
