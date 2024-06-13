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
import javafx.scene.text.*;
import jesh.project.jeshproject.model.UserManager;

public class LoginPage {
    @FXML private Text title;
    private UserManager userManager;
    @FXML private Button loginButton;
    @FXML private Button goBackButton;
    @FXML private PasswordField passwordField;
    @FXML private TextField passwordTextField;
    @FXML private TextField usernameField;
    @FXML private Label errorMessage;
    @FXML private Button SignupLink;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private CheckBox showPasswordCheckbox;

    @FXML
    public void initialize() {
        title.setText("Login");

        // Initially, set both controls visible, but hide the TextField
        passwordField.setVisible(true);
        passwordTextField.setVisible(false);

        // Add a listener to the checkbox's selected property
        showPasswordCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Checkbox is selected
                // Hide password field and show text field, copy password text
                passwordField.setVisible(false);
                passwordTextField.setVisible(true);
                passwordTextField.setText(passwordField.getText());
            } else { // Checkbox is deselected
                // Hide text field and show password field
                passwordTextField.setVisible(false);
                passwordField.setVisible(true);
            }
        });
    }

    public LoginPage() {
        userManager = new UserManager(new SqliteUserDAO());
    }

    private void setErrorMessageAndStyle(TextField field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red;");
        errorLabel.setText(errorMessage);
    }
    private void resetErrorLabelsAndStyles() {
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");

        usernameField.setStyle("");
        passwordField.setStyle("");

        errorMessage.setText("");
    }

    Connection connection = SqliteConnection.getInstance();
    //SqliteUserDAO sqliteUserDAO = new SqliteUserDAO();
    SqliteUserDAO userDAO = new SqliteUserDAO();

    @FXML
    private void login() throws IOException {
        resetErrorLabelsAndStyles();

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
            User user = userManager.getUserByUsernameAndPassword(username, password);
            if (user != null) {
                userManager.logIn(user);
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

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/MainPage.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }

    @FXML
    private void goBacktoHome() throws IOException {
        // Get the stage
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/HomePage.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }
    @FXML
    private void goToSignupPage() throws IOException {
        Stage stage = (Stage) SignupLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/SignUp.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

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

