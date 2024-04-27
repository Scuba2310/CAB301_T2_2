package jesh.project.jeshproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jesh.project.jeshproject.model.mockDB;

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


    public mockDB userDAO = new mockDB();

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
}
