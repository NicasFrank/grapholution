package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * SceneController Klasse für die Darstellung des Konfigurierbaren Hillclimbers
 */
public class SceneControllerHillclimber extends SceneController {
    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton_backwards;
    @FXML
    private Button sendButton_startAlgo;
    @FXML
    private Button sendButton_saveAlgo;
    @FXML
    private RadioButton radioMutationBinary;
    @FXML
    private RadioButton radioZeroMax;
    @FXML
    private RadioButton radioOneMax;
    @FXML
    private Slider sliderMutationChance;

    private ViewModel viewModel;
    private ViewModelHillclimber viewModelHillclimber;

    /**
     * speichert aktuellen Text des Inputfields, triggert dann laden der Ergebnis-Szene und gibt Input an ViewModel, damit
     * an Hillclimber weiter
     * Methode um Hillclimber Algorithmus mit entsprechendem ViewModel zu starten
     */
    public void sendButton_startAlgo() {
        viewModel.navigation_configureScreen("StatisticsHillclimber");
        viewModel.startHillclimberAlgorithm(
                radioMutationBinary.isSelected(),
                sliderMutationChance.getValue(),
                radioOneMax.isSelected()
        );

    }

    /**
     * Handlet die Rückwärtsnavigation
     */
    public void sendButton_backwards() {
        viewModel.navigation_Back();
    }

    /**
     * setter für allgemeines ViewModel zur Navigation
     *
     * @param viewModel
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
