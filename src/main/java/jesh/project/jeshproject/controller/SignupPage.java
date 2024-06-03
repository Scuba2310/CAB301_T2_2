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
import javafx.scene.text.*;

import java.io.IOException;
import java.sql.Connection;

public class SignupPage {
    @FXML private Text title;
    @FXML private Button goBackButton;
    @FXML private Button signUpButton;
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

    Connection connection = SqliteConnection.getInstance();
    public SignupPage() {
        userDAO = new SqliteUserDAO();
    }

    @FXML
    public void initialize() {
        title.setText("Sign Up");
    }

    @FXML
    private void signup() throws IOException {
        resetErrorLabelsAndStyles();

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String birthday = birthdayField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean hasError = false;

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
            userDAO.addUser(new User(0, firstName, lastName, birthday, email, username, password));
            successMessage();
            goToLoginPage();
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
    }

    private void setErrorMessageAndStyle(TextField field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red;");
        errorLabel.setText(errorMessage);
    }

    @FXML
    private void handleFieldInput(KeyEvent event) {
        TextField field = (TextField) event.getSource();
        field.setStyle("");
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
        User user = userDAO.getUser(email, UserIdentifierType.EMAIL);
        return user != null;
    }

    private boolean usernameExists(String username) {
        User user = userDAO.getUser(username, UserIdentifierType.USERNAME);
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
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("CSS-Styling/HomePage.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    private void goToLoginPage() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("CSS-Styling/Login.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }
}
