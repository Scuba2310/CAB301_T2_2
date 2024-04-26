package jesh.project.jeshproject.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

public class SignupPage {
    @FXML private Button goBackButton;
    @FXML private TextField birthdayField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;

    @FXML private Label firstNameErrorLabel;
    @FXML private Label lastNameErrorLabel;
    @FXML private Label birthdayErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML
    private void signup() {
        // Reset error labels and field styles
        resetErrorLabelsAndStyles();

        // Code to handle signup
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String birthday = birthdayField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean hasError = false;

        if (firstName.isEmpty()) {
            setErrorMessageAndStyle(firstNameField, firstNameErrorLabel, "Please enter your first name");
            hasError = true;
        }

        if (lastName.isEmpty()) {
            setErrorMessageAndStyle(lastNameField, lastNameErrorLabel, "Please enter your last name");
            hasError = true;
        }

        if (username.isEmpty()) {
            setErrorMessageAndStyle(usernameField, usernameErrorLabel, "Please enter your username");
            hasError = true;
        }

        if (password.isEmpty()) {
            setErrorMessageAndStyle(passwordField, passwordErrorLabel, "Please enter your password");
            hasError = true;
        }

        if (!isValidBirthday(birthday)) {
            setErrorMessageAndStyle(birthdayField, birthdayErrorLabel, "Please enter a valid birthday in the format DD/MM/YYYY.");
            hasError = true;
        }

        if (!isValidEmail(email)) {
            setErrorMessageAndStyle(emailField, emailErrorLabel, "Please enter a valid email address.");
            hasError = true;
        }

        if (hasError) {
            return;
        }

        // Proceed with signup
    }

    private void resetErrorLabelsAndStyles() {
        firstNameErrorLabel.setText("");
        lastNameErrorLabel.setText("");
        birthdayErrorLabel.setText("");
        emailErrorLabel.setText("");
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");

        firstNameField.setStyle("");
        lastNameField.setStyle("");
        birthdayField.setStyle("");
        emailField.setStyle("");
        usernameField.setStyle("");
        passwordField.setStyle("");
    }

    private void setErrorMessageAndStyle(TextField field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red;");
        errorLabel.setText(errorMessage);
    }

    @FXML
    private void handleFieldInput(KeyEvent event) {
        // Reset style and error message when the user starts typing
        TextField field = (TextField) event.getSource();
        field.setStyle("");
        switch (field.getId()) {
            case "firstNameField":
                firstNameErrorLabel.setText("");
                break;
            case "lastNameField":
                lastNameErrorLabel.setText("");
                break;
            case "birthdayField":
                birthdayErrorLabel.setText("");
                break;
            case "emailField":
                emailErrorLabel.setText("");
                break;
            case "usernameField":
                usernameErrorLabel.setText("");
                break;
            case "passwordField":
                passwordErrorLabel.setText("");
                break;
            default:
                break;
        }
    }

    private boolean isValidBirthday(String birthday) {
        return birthday.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    //private void showAlert(String title, String message) {
    //    Alert alert = new Alert(Alert.AlertType.ERROR);
    //    alert.setTitle(title);
    //    alert.setHeaderText(null);
    //    alert.setContentText(message);
    //    alert.showAndWait();
    //}

    @FXML
    private void goBacktoHome() throws IOException {
        // Get the stage
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
