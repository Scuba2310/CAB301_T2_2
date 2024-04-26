module jesh.project.jeshproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens jesh.project.jeshproject to javafx.fxml;
    exports jesh.project.jeshproject;
}