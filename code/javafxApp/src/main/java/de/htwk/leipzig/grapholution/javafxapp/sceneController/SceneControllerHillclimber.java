package de.htwk.leipzig.grapholution.javafxapp.sceneController;

import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.javafxapp.enums.EChoices;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import de.htwk.leipzig.grapholution.javafxapp.viewModel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

/**
 * SceneController für das Fenster zur Konfiguration des Hillclimbers
 */
public class SceneControllerHillclimber extends SceneController {
    public RadioButton radioMutationBinary;
    @FXML
    private TextField inputField;
    @FXML
    private Slider sliderMutationChance;

    /**
     * speichert aktuellen Text des Inputfields, triggert dann laden der Ergebnis-Szene und gibt Input an ViewModel, damit
     * an Hillclimber weiter
     * Methode um Hillclimber Algorithmus mit entsprechendem ViewModel
     */
    public void sendButton_startAlgo() {
        viewModel.startHillclimberAlgorithm(createConfigOptions(), inputField.textProperty().get());
        viewModel.navigation_configureScreen(EChoices.ResultsHillclimber);
    }

    /**
     *
     * @return gibt gewünschte Konfigurations-Optionen zurück
     * hier: Binäre-Mutation, Wahrscheinlichkeit der Mutation, Lösung des OneMaxProblem
     */
    private AlgorithmConfigOptions createConfigOptions(){
        return new AlgorithmConfigOptions()
                .add(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected())
                .add(IntConfig.MutationChance, radioMutationBinary.isSelected() ?(int) sliderMutationChance.getValue(): 0)
                .add(BoolConfig.FitnessIsOneMax, viewModel.getProblemIsOneMax());
    }

    /**
     * Setzt die gewünschten Optionen für den konfiguierbaren Hillclimber-Algorithmus
     * @param options Hillclimber Konfigurations-Optionen
     */
    private void setOptions(AlgorithmConfigOptions options){
        radioMutationBinary.selectedProperty().set(options.getOrElse(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected()));
        sliderMutationChance.valueProperty().set(options.getOrElse(IntConfig.MutationChance, (int) sliderMutationChance.getValue()));
    }

    /**
     * Öffnet einen Dialog zum Speichern der Algorithmus-Konfiguration
     */
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
     * Setzt für jeden SceneController das grundlegende viewModel
     * @param viewModel das zu setzende viewModel
     */
    @Override
    public void setViewModel(ViewModel viewModel) {
        super.setViewModel(viewModel);
        setOptions(viewModel.getConfigOptions());
    }
}
