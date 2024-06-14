package jesh.project.jeshproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jesh.project.jeshproject.HelloApplication;
import jesh.project.jeshproject.brightness.BrightnessManager;
import jesh.project.jeshproject.model.*;

import java.io.IOException;
import java.util.ArrayList;

public class MainPage {
    public HBox timeline_main;
    public Button Save_timeline_button;
    @FXML private Button nightModeButton;
    @FXML private Slider brightnessSlider;
    @FXML private Text brightnessLevelTitle;
    @FXML private Button timeslot_1;
    @FXML private Button timeslot_2;
    @FXML private Button timeslot_3;
    @FXML private Button timeslot_4;
    @FXML private Button timeslot_5;
    @FXML private Text timelineName;
    @FXML private Label start_message;
    @FXML private Button saveButton;
    @FXML private VBox infoBox;
    @FXML private Button signoutButton;
    @FXML private Button profileButton;
    @FXML private Button settingsButton;
    @FXML private Button testButton;
    @FXML private TextField timeline_name;
    @FXML private TextField sleepwell_logo;
    @FXML private Button save_button;
    @FXML private Button add_time_button;
    @FXML private TextField start_time_slider;
    @FXML private TextField end_time_slider;
    @FXML private TextField NM_start_title;
    @FXML private TextField NM_end_title;
    @FXML private TextField NM_start;
    @FXML private TextField NM_end;
    @FXML private Button NM_button;
    @FXML private TextField BL_title;
    @FXML private Slider brightness_slider;

    private final TimelineManager timelineManager;
    private final UserManager userManager;

    private int currentTimeline;

    public MainPage() {
        SqliteUserDAO userDAO = new SqliteUserDAO();
        userManager = new UserManager(userDAO);
        timelineManager = new TimelineManager(userDAO);
    }

    public void testBrightness(ActionEvent actionEvent) {
        try {
            BrightnessManager.ChangeBrightness((int) brightness_slider.getValue());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void saveTimeline(ActionEvent actionEvent) {
        int id = currentTimeline;
        String name = timeline_name.getText();
        int startTime = Integer.parseInt(start_time_slider.getText());
        int endTime =  Integer.parseInt(end_time_slider.getText());
        int brightness = (int) brightness_slider.getValue();
        int userID = userManager.getLoggedInUser().getId();

        // Adjust constructor call to match the Timeline class definition
        Timeline newTimeline = new Timeline(id, name, startTime, endTime, brightness, userID);

        timelineManager.updateTimeline(newTimeline);
    }

    @FXML
    public void getTimeline() {
        int userID = userManager.getLoggedInUser().getId();
        ArrayList<String> timelines = timelineManager.getUserTimelines(userID);
    }

    @FXML
    private void loadTimeline(int value) {
        timeline_name.setEditable(true);
        start_time_slider.setEditable(true);
        end_time_slider.setEditable(true);
        start_message.setVisible(false);

        Timeline timeline = timelineManager.getTimelineByID(currentTimeline);
        timeline_name.setText(timeline.getName());
        // Convert int to String before setting the text
        start_time_slider.setText(Integer.toString(timeline.getStartTime()));
        end_time_slider.setText(Integer.toString(timeline.getEndTime()));
        brightness_slider.setValue(timeline.getBrightness());
    }

    @FXML
    private void load_T1() {
        int value = 0;
        currentTimeline = value;
        loadTimeline(value);
    }
    @FXML
    private void load_T2() {
        int value = 1;
        currentTimeline = value;
        loadTimeline(value);
    }
    @FXML
    private void load_T3() {
        int value = 2;
        currentTimeline = value;
        loadTimeline(value);
    }
    @FXML
    private void load_T4() {
        int value = 3;
        currentTimeline = value;
        loadTimeline(value);
    }
    @FXML
    private void load_T5() {
        int value = 4;
        currentTimeline = value;
        loadTimeline(value);
    }

    @FXML
    private void goToProfilePage() throws IOException {
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ProfilePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("CSS-Styling/Profile.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }
}
