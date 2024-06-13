package jesh.project.jeshproject.controller;

import jesh.project.jeshproject.HelloApplication;
import jesh.project.jeshproject.exceptions.*;
import jesh.project.jeshproject.model.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jesh.project.jeshproject.model.UserManager;

public class SignupPage {
<<<<<<< HEAD
    private UserManager userManager;
    @FXML
    private Button goBackButton;
    @FXML
    private Button signUpButton;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private Label firstNameErrorLabel;
    @FXML
    private Label lastNameErrorLabel;
    @FXML
    private Label birthdayErrorLabel;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label usernameErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label errorMessage;

=======
    @FXML private Button signUpButton;
    private UserManager userManager;
    @FXML private Text title;
    @FXML private Button goBackButton;
    @FXML private TextField birthdayField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private Button loginLink;
    @FXML private Label firstNameErrorLabel;
    @FXML private Label lastNameErrorLabel;
    @FXML private Label birthdayErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label usernameErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private IUserDAO userDAO;
    @FXML private Label errorMessage;
>>>>>>> 86499e57984c72902f940300cf51c57558cf467d

    Connection connection = SqliteConnection.getInstance();
    //SqliteUserDAO sqliteUserDAO = new SqliteUserDAO();
    public SignupPage() {
        userManager = new UserManager(new SqliteUserDAO());
    }

    @FXML
<<<<<<< HEAD
    private void signup() throws IOException {
=======
    public void initialize() {
        title.setText("Sign Up");
    }
    @FXML
    private void signup() throws IOException, SQLException {
>>>>>>> 86499e57984c72902f940300cf51c57558cf467d
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


        // experimental code that should make unit testing easier
        String[] enteredAttributes = {firstName, lastName, birthday, email, username, password};
        String[] attributes = {"first name", "last name", "birthday", "email", "username", "password"};
        TextField[] attributeFields = {firstNameField, lastNameField, birthdayField, emailField, usernameField, passwordField};
        Label[] attributeLabels = {firstNameErrorLabel, lastNameErrorLabel, birthdayErrorLabel, emailErrorLabel, usernameErrorLabel, passwordErrorLabel};

        for (int i = 0; i < attributes.length; i++) {
            try {
                if (enteredAttributes[i].isEmpty()) {
                    throw new EmptyFieldException("Please enter your " + attributes[i]);
                } else if (attributes[i].equals("birthday") & !isValidBirthday(birthday)) {
                    throw new InvalidFieldException("Please enter a valid birthday in the format DD/MM/YYYY.");
                } else if (attributes[i].equals("email")) {
                    if (!isValidEmail(email)) {
                        throw new InvalidFieldException("Please enter a valid email address.");
                    } else if (emailExists(email)) {
                        throw new InvalidFieldException("An account with this email already exists.");
                    }
                } else if (usernameExists(username)) {
                    throw new InvalidFieldException("Username is already taken.");
                }
            } catch (Exception exception) {
                hasError = true;
                setErrorMessageAndStyle(attributeFields[i], attributeLabels[i], exception.getMessage());
            }
        }

        if (!hasError) {
            // All fields are valid
            User newUser = new User(0, firstName, lastName, birthday, email, username, password);
            boolean isUserAdded = userManager.addUser(newUser);

            if (isUserAdded) {
                successMessage();

                // Proceed to the home page or any other action
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
                stage.setScene(scene);
            } else {
                // Check if the user already exists
                if (userManager.getUser(email, UserIdentifierType.EMAIL) != null) {
                    errorMessage.setText("User already exists");
                } else {
                    errorMessage.setText("Error adding user to the database");
                }
            }
        } else {
            errorMessage.setText("User could not be added");
        }
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
        errorMessage.setText("");
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
        // possibly change switch statement to field.setText(""); ??
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

    private boolean emailExists(String email) {
        // Check if a user with the provided email exists
        User user = userManager.getUser(email, UserIdentifierType.EMAIL);
        return user != null;
    }

    private boolean usernameExists(String username) {
        // Check if a user with the provided username exists
        User user = userManager.getUser(username, UserIdentifierType.USERNAME);
        return user != null;
    }

    private void successMessage() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("New user successfully created");
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        alert.show();
    }

    @FXML
    private void goBacktoHome() throws IOException {
        // Get the stage
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
<<<<<<< HEAD
=======

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/HomePage.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }
    @FXML
    private void goToHomePage() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/MainPage.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }

    @FXML
    private void goToLoginPage() throws IOException {
        Stage stage = (Stage) loginLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/Login.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

>>>>>>> 86499e57984c72902f940300cf51c57558cf467d
        stage.setScene(scene);
    }
}

