package jesh.project.jeshproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jesh.project.jeshproject.HelloApplication;
import jesh.project.jeshproject.model.SqliteUserDAO;
import jesh.project.jeshproject.model.UserManager;
import jesh.project.jeshproject.model.User;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.util.EventListener;

public class ProfilePage {

    private UserManager userManager;

    @FXML private Button goBackButton;
    @FXML private Button logOutButton;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField birthdayField;
    @FXML private TextField emailField;
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;

    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    @FXML private Label messageLabel = new Label();


    public ProfilePage() {
        userManager = new UserManager(new SqliteUserDAO());
    }



    @FXML
    private void viewDetails() {
        User user = userManager.getLoggedInUser();

        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        birthdayField.setText(user.getBirthday());
        emailField.setText(user.getEmail());
        userNameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
        birthdayField.setEditable(true);
        emailField.setEditable(true);
        userNameField.setEditable(true);
        passwordField.setEditable(true);
    }
    @FXML
    private void hideDetails() {
        firstNameField.setText("");
        lastNameField.setText("");
        birthdayField.setText("");
        emailField.setText("");
        userNameField.setText("");
        passwordField.setText("");
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        birthdayField.setEditable(false);
        emailField.setEditable(false);
        userNameField.setEditable(false);
        passwordField.setEditable(false);
    }

    @FXML
    private void saveChanges() {
        User oldUser = userManager.getLoggedInUser();

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String birthday = birthdayField.getText();
        String email = emailField.getText();
        String username = userNameField.getText();
        String password = passwordField.getText();
        if (firstName.isEmpty()) { firstName = oldUser.getFirstName(); }
        if (lastName.isEmpty()) { lastName = oldUser.getLastName(); }
        if (birthday.isEmpty()) { birthday = oldUser.getBirthday(); }
        if (email.isEmpty()) { email = oldUser.getEmail(); }
        if (username.isEmpty()) { username = oldUser.getUsername(); }
        if (password.isEmpty()) { password = oldUser.getPassword(); }

        User updatedUser = new User(oldUser.getId(), firstName, lastName, birthday, email, username, password);
        boolean updated = userManager.updateUser(updatedUser);
        if (updated) { messageLabel.setText("User updated"); }
        else { messageLabel.setText("Unable to update user"); }
    }

    @FXML
    private void deleteUser() throws IOException {
        userManager.deleteUser();
        logOut();
    }

    @FXML
    private void goBackToMain() throws IOException {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void logOut() throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

}
