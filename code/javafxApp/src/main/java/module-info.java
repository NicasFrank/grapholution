module code.Grapholution.javafxApp.main {
    //requires code.Grapholution.evoLibrary.main;
    requires org.apache.commons.text;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
  requires code.Grapholution.evoLibrary.main;
  opens de.htwk.leipzig.grapholution.javafxapp to javafx.fxml;
  opens de.htwk.leipzig.grapholution.javafxapp.model to javafx.base;
    exports de.htwk.leipzig.grapholution.javafxapp;
    exports de.htwk.leipzig.grapholution.javafxapp.model;
}