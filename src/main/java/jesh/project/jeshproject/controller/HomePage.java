package jesh.project.jeshproject.controller;

import jesh.project.jeshproject.HelloApplication;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.io.IOException;

public class HomePage {
    @FXML
    private Text title;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    @FXML
    public void initialize() {
        title.setText("JESH PROJECT NAME !! ");
    }

    //public mockDB userDAO = new mockDB();

    // Code to switch to login page
    // Load LoginPage.fxml and switch scene
    @FXML
    private void goToLoginPage() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    // Code to switch to signup page
    // Load SignupPage.fxml and switch scene
    @FXML
    private void goToSignupPage() throws IOException {
        Stage stage = (Stage) signupButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    // DELETE WHEN TESTING IS DONE ** as well as fxml button
    @FXML Button bypassButton;
    @FXML
    private void goToMainPage() throws IOException {
        Stage stage = (Stage) bypassButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}