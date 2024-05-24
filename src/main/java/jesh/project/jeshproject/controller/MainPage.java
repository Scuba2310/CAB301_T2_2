package jesh.project.jeshproject.controller;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.*;
import javafx.fxml.*;
import jesh.project.jeshproject.HelloApplication;

public class MainPage {
    @FXML private Button nightModeButton;
    @FXML private Button profileButton;
    @FXML private Button signoutButton;
    @FXML private Button settingsButton;
    @FXML private Button saveButton;
    @FXML private Button testButton;

    @FXML private Slider brightnessSlider;

    @FXML private Text brightnessLevelTitle;
    @FXML private Text endTimeText;
    @FXML private Text startTimeText;
    @FXML private Text timelineName;

    @FXML private VBox infoBox;


    @FXML
    private TextField timeline_name;

    @FXML
    private TextField sleepwell_logo;

    @FXML
    private TextField BL_title;

    @FXML
    private TextField NM_start_title;

    @FXML
    private TextField NM_end_title;

    @FXML
    private TextField NM_start;

    @FXML
    private TextField NM_end;

    @FXML
    private Button save_button;

    @FXML
    private Button add_time_button;

    @FXML
    private Button NM_button;

    @FXML
    private Slider start_time_slider;

    @FXML
    private Slider end_time_slider;

    @FXML
    private Slider brightness_slider;


}
