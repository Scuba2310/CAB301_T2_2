
/**
package jesh.project.jeshproject.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.fxml.FXML;

import java.io.IOException;

public class LoginPage {
    @FXML private Button goBackButton;
    @FXML private TextField passwordField;
    @FXML private TextField usernameField;


    @FXML
    private void login() {
        // Code to handle login
        String username = usernameField.getText();
        String password = passwordField.getText();

        //check username and password against database
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

**/