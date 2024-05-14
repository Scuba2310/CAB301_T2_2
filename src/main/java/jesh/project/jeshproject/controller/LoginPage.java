package jesh.project.jeshproject.controller;

import javafx.scene.input.KeyEvent;
import jesh.project.jeshproject.HelloApplication;
import jesh.project.jeshproject.model.SqliteConnection;
import jesh.project.jeshproject.model.SqliteUserDAO;
import jesh.project.jeshproject.model.User;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import java.io.IOException;
import java.sql.Connection;

public class LoginPage {
    @FXML private Button loginButton;
    @FXML private Button goBackButton;
    @FXML private TextField passwordField;
    @FXML private TextField usernameField;
    @FXML private Label errorMessage;

    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;

    private void setErrorMessageAndStyle(TextField field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red;");
        errorLabel.setText(errorMessage);
    }
    private void resetErrorLabelsAndStyles() {
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");

        usernameField.setStyle("");
        passwordField.setStyle("");
    }

    Connection connection = SqliteConnection.getInstance();
    //SqliteUserDAO sqliteUserDAO = new SqliteUserDAO();
    SqliteUserDAO userDAO = new SqliteUserDAO();

    @FXML
    private void login() throws IOException {
        resetErrorLabelsAndStyles();
        errorMessage.setText("");

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty()) {
                setErrorMessageAndStyle(usernameField, usernameErrorLabel, "Please enter your username");
            }
            if (password.isEmpty()) {
                setErrorMessageAndStyle(passwordField, passwordErrorLabel, "Please enter your password");
            }
        } else {
            User user = userDAO.getUserByUsernameAndPassword(username, password);
            if (user != null) {
                goToMainPage();
            } else {
                errorMessage.setText("Username or password is incorrect.");
            }
        }
    }

    @FXML
    private void goToMainPage() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void goBacktoHome() throws IOException {
        // Get the stage
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void handleFieldInput(KeyEvent event) {
        // Reset style and error message when the user starts typing
        TextField field = (TextField) event.getSource();
        field.setStyle("");
        // possibly change switch statement to field.setText(""); ??
        switch (field.getId()) {
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
}

