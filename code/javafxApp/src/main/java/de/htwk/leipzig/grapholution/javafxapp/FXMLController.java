package de.htwk.leipzig.grapholution.javafxapp;

import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    Button startButton;
    @FXML
    TextField inputField;
    @FXML
    TextField outputField;

    ViewModel viewModel = new ViewModel();

    public void initialize() {
        inputField.textProperty().bindBidirectional(viewModel.inputFieldProperty());
        outputField.textProperty().bind(viewModel.outputFieldProperty());
    }

    public void placeholder(ActionEvent actionEvent) {
        viewModel.onButtonClick();
    }
}