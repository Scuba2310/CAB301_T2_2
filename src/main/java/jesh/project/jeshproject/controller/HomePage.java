package jesh.project.jeshproject.controller;

import jesh.project.jeshproject.HelloApplication;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;
import jesh.project.jeshproject.model.SqliteConnection;
import jesh.project.jeshproject.model.SqliteUserDAO;

import java.io.IOException;
import java.sql.Connection;

public class HomePage {
    public Text description;
    @FXML
    private Text title;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    public HomePage(){
//        Connection connection = SqliteConnection.getInstance();
//        SqliteUserDAO userDAO = new SqliteUserDAO();
    }

    @FXML
    public void initialize() {
        title.setText("SleepWell");
        description.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        description.setWrappingWidth(600);
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

    // DELETE WHEN TESTING IS DONE ** as well as fxml button
    @FXML Button bypassButton;
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