module code.Grapholution.javafxApp.main {
    //requires code.Grapholution.evoLibrary.main;
    requires org.apache.commons.text;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    opens de.htwk.leipzig.grapholution.javafxapp to javafx.fxml;
    exports de.htwk.leipzig.grapholution.javafxapp;
}