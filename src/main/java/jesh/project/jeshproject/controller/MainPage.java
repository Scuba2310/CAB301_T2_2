package jesh.project.jeshproject.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.fxml.FXML;
import jesh.project.jeshproject.brightness.BrightnessManager;
import jesh.project.jeshproject.model.IUserDAO;
import jesh.project.jeshproject.model.SqliteUserDAO;
import jesh.project.jeshproject.model.TimelineManager;
import jesh.project.jeshproject.model.UserManager;

public class MainPage {
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

    private int userID;


    private TimelineManager timelineManager;


    public MainPage(int userID) {
         timelineManager = new TimelineManager(new SqliteUserDAO());
         this.userID = userID;
    }

    public int getUserID() {return userID;}
    public static void setUserID(int userId) {this.userID = userId;}

    public void testBrightness(ActionEvent actionEvent) {
        try{
            BrightnessManager.ChangeBrightness((int) brightness_slider.getValue());
        } catch (Exception exception) {

        }
    }

    public void saveTimeline(ActionEvent actionEvent) {
        String name = timeline_name.getText();
        int startTime = (int) start_time_slider.getValue();
        int endTime = (int) end_time_slider.getValue();
//        int userID =
//        if (timelineManager.getTimeline())
    }


}
