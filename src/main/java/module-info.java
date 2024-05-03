module jesh.project.jeshproject {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;

    exports jesh.project.jeshproject;
    opens jesh.project.jeshproject;
    opens jesh.project.jeshproject.controller to javafx.fxml;
    exports jesh.project.jeshproject.controller to javafx.fxml;
    exports jesh.project.jeshproject.exceptions;
    opens jesh.project.jeshproject.exceptions to javafx.fxml;
    exports jesh.project.jeshproject.model to tests;
    opens jesh.project.jeshproject.model to tests;
}