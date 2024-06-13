package jesh.project.jeshproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    @FXML private Text endTimeText;
    @FXML private Text startTimeText;
    @FXML private Text timelineName;
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
    @FXML private ChoiceBox<String> timelineChoiceBox;

<<<<<<< HEAD
    @FXML
    private TextField timeline_name;
    @FXML
    private TextField sleepwell_logo;
    @FXML
    private Button save_button;
    @FXML
    private Button add_time_button;
    @FXML
    private Slider start_time_slider;
    @FXML
    private Slider end_time_slider;
    @FXML
    private TextField NM_start_title;
    @FXML
    private TextField NM_end_title;
    @FXML
    private TextField NM_start;
    @FXML
    private TextField NM_end;
    @FXML
    private Button NM_button;
    @FXML
    private TextField BL_title;
    @FXML
    private Slider brightness_slider;

    @FXML private ChoiceBox timelineChoiceBox;


    private TimelineManager timelineManager;
    private UserManager userManager;

=======
    private final TimelineManager timelineManager;
    private final UserManager userManager;
>>>>>>> 86499e57984c72902f940300cf51c57558cf467d

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
        String name = timeline_name.getText();
        int startTime = Integer.parseInt(start_time_slider.getText());
        int endTime =  Integer.parseInt(end_time_slider.getText());
        int brightness = (int) brightness_slider.getValue();
        int userID = userManager.getLoggedInUser().getId();

        // Adjust constructor call to match the Timeline class definition
        Timeline newTimeline = new Timeline(0, name, startTime, endTime, brightness, userID);
        Timeline existingTimeline = timelineManager.getTimeline(name, userID);

        if (existingTimeline != null) {
            timelineManager.updateTimeline(newTimeline);
        } else {
            timelineManager.addTimeline(newTimeline);
        }

        updateTimelineChoiceBox();
    }

    @FXML
    public void updateTimelineChoiceBox() {
        int userID = userManager.getLoggedInUser().getId();
        ArrayList<String> timelines = timelineManager.getUserTimelines(userID);
        timelineChoiceBox.setItems(FXCollections.observableArrayList(timelines));
    }

    @FXML
    private void loadTimeline() {
        String selectedValue = timelineChoiceBox.getValue();
        int userID = userManager.getLoggedInUser().getId();

        if (selectedValue != null) {
            Timeline timeline = timelineManager.getTimeline(selectedValue, userID);
            timeline_name.setText(timeline.getName());
            // Convert int to String before setting the text
            start_time_slider.setText(Integer.toString(timeline.getStartTime()));
            end_time_slider.setText(Integer.toString(timeline.getEndTime()));
            brightness_slider.setValue(timeline.getBrightness());
        }
    }

    @FXML
    private void goToProfilePage() throws IOException {
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ProfilePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
