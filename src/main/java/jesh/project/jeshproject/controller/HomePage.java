package jesh.project.jeshproject.controller;

import jesh.project.jeshproject.HelloApplication;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;
import jesh.project.jeshproject.model.IUserDAO;
import jesh.project.jeshproject.model.SqliteConnection;
import jesh.project.jeshproject.model.SqliteTimelinesDAO;
import jesh.project.jeshproject.model.SqliteUserDAO;

import java.io.IOException;
import java.sql.Connection;
import jesh.project.jeshproject.model.UserManager;

public class HomePage {
    public Text description;
    @FXML
    private Text title;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;
    @FXML
    private Button bypassButton;

    private UserManager userManager;
    private IUserDAO userDAO;

    public HomePage(){
        userManager = new UserManager(new SqliteUserDAO());
        userManager.logOut(); // make sure all users are signed out
    }

    @FXML
    public void initialize() {
        title.setText("SleepWell");
        description.setText("Are you struggling to get a good night's sleep because of screen time? Meet SleepWell, the app designed to reduce visual strain and improve sleep quality. Customize your screen brightness and color based on your schedule, and minimize blue light exposure in the evening. Enhance your digital wellness and wake up refreshed with SleepWell. Start your journey to better sleep today!");
        description.setWrappingWidth(600);
        description.setTextAlignment(TextAlignment.CENTER);
    }

    //SqliteUserDAO userDAO = new SqliteUserDAO();
    // Code to switch to login page
    // Load LoginPage.fxml and switch scene
    @FXML
    private void goToLoginPage() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/Login.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }

    // Code to switch to signup page
    // Load SignupPage.fxml and switch scene
    @FXML
    private void goToSignupPage() throws IOException {
        Stage stage = (Stage) signupButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/SignUp.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }

    @FXML
    private void goToMainPage() throws IOException {
        Stage stage = (Stage) bypassButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/MainPage.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }
}