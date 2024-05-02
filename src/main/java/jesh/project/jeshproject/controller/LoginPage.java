package jesh.project.jeshproject.controller;

import jesh.project.jeshproject.HelloApplication;
import jesh.project.jeshproject.model.User;
import jesh.project.jeshproject.model.mockDB;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import java.io.IOException;
import javafx.scene.text.*;

public class LoginPage {
    @FXML private Text title;
    @FXML private Button loginButton;
    @FXML private Button goBackButton;
    @FXML private TextField passwordField;
    @FXML private TextField usernameField;
    @FXML private Label errorMessage;

    @FXML
    public void initialize() {
        title.setText("Login");
    }

    mockDB userDAO = new mockDB();

    @FXML
    private void login() throws IOException {
        // Code to handle login
        String username = usernameField.getText();
        String password = passwordField.getText();

        //check username and password against database

        for (User user : mockDB.users) {
            if (username.equals(user.getUsername()) & password.equals(user.getPassword())) {
                try {
                    goToMainPage();
                } catch (IOException exception) {
                    // ????
                }
                break;
            }
            else {
                errorMessage.setText("Username or password is incorrect.");
            }
        }

        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
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

