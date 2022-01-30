package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

/**
 * SceneController Klasse für die Darstellung des Konfigurierbaren Hillclimbers
 */
public class SceneControllerHillclimber extends SceneController {
    public RadioButton radioMutationBinary;
    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton_backwards;
    @FXML
    private Button sendButton_startAlgo;
    @FXML
    private Button sendButton_saveAlgo;
    @FXML
    private RadioButton radioZeroMax;
    @FXML
    private RadioButton radioOneMax;
    @FXML
    private Slider sliderMutationChance;

    private ViewModel viewModel;

    /**
     * speichert aktuellen Text des Inputfields, triggert dann laden der Ergebnis-Szene und gibt Input an ViewModel, damit
     * an Hillclimber weiter
     * Methode um Hillclimber Algorithmus mit entsprechendem ViewModel zu starten
     */
    public void sendButton_startAlgo() {
        viewModel.startHillclimberAlgorithm(createConfigOptions(),this);
        viewModel.navigation_configureScreen(EChoices.ResultsHillclimber);
    }

    private AlgorithmConfigOptions createConfigOptions(){
        return new AlgorithmConfigOptions()
                .add(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected())
                .add(IntConfig.MutationChance, radioMutationBinary.isSelected() ?(int) sliderMutationChance.getValue(): 0)
                .add(BoolConfig.FitnessIsOneMax, radioOneMax.isSelected());
    }

    private void setOptions(AlgorithmConfigOptions options){
        radioMutationBinary.selectedProperty().set(options.getOrElse(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected()));
        sliderMutationChance.valueProperty().set(options.getOrElse(IntConfig.MutationChance, (int) sliderMutationChance.getValue()));

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

    public TextField getInputField(){
        return inputField;
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
