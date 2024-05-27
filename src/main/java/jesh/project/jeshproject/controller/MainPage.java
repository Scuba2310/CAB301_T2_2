package jesh.project.jeshproject.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.fxml.FXML;

public class MainPage {
    @FXML private Button nightModeButton;
    @FXML private Slider brightnessSlider;
    @FXML private Text brightnessLevelTitle;
    @FXML private Button timeslot_1;
    @FXML private Button timeslot_2;
    @FXML private Button timeslot_3;
    @FXML private Button timeslot_4;
    @FXML private Button timeslot_5;
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
    private Text start_time_slider;


    @FXML
    private Text end_time_slider;

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



    public class BrightnessManager {
        public static void setBrightness(int brightness)
                throws IOException {
            //Creates a powerShell command that will set the brightness to the requested value (0-100), after the requested delay (in milliseconds) has passed.
            String s = String.format("$brightness = %d;", brightness)
                    + "$delay = 0;"
                    + "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;"
                    + "$myMonitor.wmisetbrightness($delay, $brightness)";
            String command = "powershell.exe  " + s;
            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);

            powerShellProcess.getOutputStream().close();

            //Report any error messages
            String line;

            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            line = stderr.readLine();
            if (line != null) {
                System.err.println("Standard Error:");
                do {
                    System.err.println(line);
                } while ((line = stderr.readLine()) != null);

            }
            stderr.close();

        }
    }




}


