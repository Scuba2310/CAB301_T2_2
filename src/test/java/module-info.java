open module tests {

    //requires org.junit.jupiter.api;
    //requires org.junit.jupiter.engine;
    //requires org.junit.platform.commons;
    //requires org.junit.platform.engine;

    requires jesh.project.jeshproject;
    requires org.junit.jupiter.api;

    //opens jesh.project.jeshproject;

    exports tests;
}