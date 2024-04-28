module jesh.project.jeshproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;

    opens jesh.project.jeshproject to javafx.fxml;
    exports jesh.project.jeshproject;
    exports jesh.project.jeshproject.exceptions;
    opens jesh.project.jeshproject.exceptions to javafx.fxml;
}