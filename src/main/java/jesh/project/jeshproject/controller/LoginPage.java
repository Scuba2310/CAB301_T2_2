package jesh.project.jeshproject.controller;

import jesh.project.jeshproject.HelloApplication;
import jesh.project.jeshproject.model.SqliteUserDAO;
import jesh.project.jeshproject.model.User;
import jesh.project.jeshproject.model.mockDB;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import java.io.IOException;

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

    SqliteUserDAO userDAO = new SqliteUserDAO();

    @FXML
    private void login() throws IOException {

        resetErrorLabelsAndStyles();
        errorMessage.setText("");

        boolean correctUser = false; // guilty until proven innocent
        boolean emptyField = false;

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() | password.isEmpty()) {
            if (username.isEmpty()) {
                setErrorMessageAndStyle(usernameField, usernameErrorLabel, "Please enter your username");
                emptyField = true;
            }
            if (password.isEmpty()) {
                setErrorMessageAndStyle(passwordField, passwordErrorLabel, "Please enter your password");
                emptyField = true;
            }
        }
        else { // don't bother checking db if a field is empty
            for (User user : userDAO.getAllUsers()) {
                if (username.equals(user.getUsername()) & password.equals(user.getPassword())) {
                    correctUser = true;
                    break;
                }
            }
        }

        if (!correctUser & !emptyField) {
            errorMessage.setText("Username or password is incorrect.");
        }
        else if (!emptyField) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
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
}

