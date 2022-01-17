package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

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
        viewModel.startHillclimberAlgorithm(createConfigOptions());
        viewModel.navigation_configureScreen(EChoices.ResultsHillclimberAlgorithm);
    }

    private AlgorithmConfigOptions createConfigOptions(){
        return new AlgorithmConfigOptions()
                .add(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected())
                .add(IntConfig.MutationChance, radioMutationBinary.isSelected() ?(int) sliderMutationChance.getValue(): 0)
                .add(BoolConfig.FitnessIsOneMax, radioOneMax.isSelected())
                .add(BoolConfig.FitnessIsZeroMax, radioZeroMax.isSelected());

    }

    private void setOptions(AlgorithmConfigOptions options){
        radioMutationBinary.selectedProperty().set(options.getOrElse(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected()));
        sliderMutationChance.selectedProperty().set(options.getOrElse(IntConfig.MutationChance, (int) sliderMutationChance.getValue()));

        if(options.getOrElse(BoolConfig.FitnessIsOneMax, radioOneMax.isSelected())){
            radioOneMax.selectedProperty().set(true);
        } else{
            radioZeroMax.selectedProperty().set(true);
        }
    }

    public void sendButton_saveConfig(){
        var fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Hillclimber Algorithmus (*.gacf)", "*.gacf"));
        var file = fileChooser.showSaveDialog(null);

        if(file != null){
            try {
                createConfigOptions().serialize(file);
            }catch (Exception e){
                DialogUtils.ShowAlert("Error","Fehler beim Speichern der Datei!");
            }
        }
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
