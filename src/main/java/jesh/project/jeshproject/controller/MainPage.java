package jesh.project.jeshproject.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import jesh.project.jeshproject.HelloApplication;
import jesh.project.jeshproject.exceptions.EmptyFieldException;
import jesh.project.jeshproject.exceptions.InvalidFieldException;
import jesh.project.jeshproject.model.SqliteTimelinesDAO;
import jesh.project.jeshproject.model.Timeline;
import jesh.project.jeshproject.model.User;

public class MainPage {
    @FXML private Button Save_timeline_button;
    @FXML private Button timeslot_1;
    @FXML private Button timeslot_2;
    @FXML private Button timeslot_3;
    @FXML private Button timeslot_4;
    @FXML private Button timeslot_5;


    @FXML private Button testButton;

    @FXML
    private TextField timeline_name;

    @FXML
    private TextField NM_start;

    @FXML
    private TextField NM_end;

    @FXML
    private Slider brightness_slider;


    @FXML
    private void testBrightness() {
        System.out.println("Test button clicked!");
        try {
            BrightnessManager.setBrightness((int) brightness_slider.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    @FXML
    private void saveTimeline() throws IOException {

        // Code to handle signup
        String Start_Time = NM_start.getText();
        String End_Time = NM_end.getText();

        int Timeslot = 1;
        int User_ID = 1;

        SqliteTimelinesDAO TimelinesDAO = new SqliteTimelinesDAO();

        TimelinesDAO.addTimeline(new Timeline(Timeslot, Start_Time, End_Time, User_ID));

        Stage stage = (Stage) Save_timeline_button.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }



}


