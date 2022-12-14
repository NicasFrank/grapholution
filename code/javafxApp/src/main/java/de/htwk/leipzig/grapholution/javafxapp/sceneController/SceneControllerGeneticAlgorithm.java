package de.htwk.leipzig.grapholution.javafxapp.sceneController;

import de.htwk.leipzig.grapholution.evolibrary.models.*;
import de.htwk.leipzig.grapholution.javafxapp.enums.EChoices;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import de.htwk.leipzig.grapholution.javafxapp.viewModel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

/**
 * SceneController für das Fenster zur Konfiguration des genetischen Algorithmus
 */
public class SceneControllerGeneticAlgorithm extends SceneController{

  @FXML
  private RadioButton radioMutationBinary;
  @FXML
  private Slider sliderGenerations;
  @FXML
  private Slider sliderRecombinationChance;
  @FXML
  private Slider sliderMutationChance;
  @FXML
  private Slider sliderPopulationSize;
  @FXML
  private Slider sliderGenotypeSize;
  @FXML
  private CheckBox checkBoxStepByStep;

  /**
   * loest Laden der naechsten Pane aus
   * gibt Konfiguration des genetischen Algorithmus weiter
   */
  public void sendButton_startAlgo(){
    viewModel.startGeneticAlgorithm(createConfigOptions());
    viewModel.navigation_configureScreen(EChoices.ResultsGeneticAlgorithm);
  }

  private AlgorithmConfigOptions createConfigOptions() {
    return new AlgorithmConfigOptions()
            .add(BoolConfig.IsStepByStep, checkBoxStepByStep.isSelected())
            .add(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected())
            .add(IntConfig.MutationChance, radioMutationBinary.isSelected() ? (int) sliderMutationChance.getValue() : 0)
            .add(BoolConfig.FitnessIsOneMax, viewModel.getProblemIsOneMax())
            .add(DoubleConfig.RecombinationChance, sliderRecombinationChance.getValue())
            .add(IntConfig.PopulationSize, (int) sliderPopulationSize.getValue())
            .add(IntConfig.GenotypeSize, (int) sliderGenotypeSize.getValue())
            .add(IntConfig.Limit, (int) sliderGenerations.getValue());
  }

  private void setOptions(AlgorithmConfigOptions options) {
    checkBoxStepByStep.selectedProperty().set(options.getOrElse(BoolConfig.IsStepByStep, checkBoxStepByStep.isSelected()));
    radioMutationBinary.selectedProperty().set(options.getOrElse(BoolConfig.MutationIsBinary, radioMutationBinary.isSelected()));
    sliderMutationChance.valueProperty().set(options.getOrElse(IntConfig.MutationChance, (int) sliderMutationChance.getValue()));
    sliderRecombinationChance.valueProperty().set(options.getOrElse(DoubleConfig.RecombinationChance, sliderRecombinationChance.getValue()));
    sliderPopulationSize.valueProperty().set(options.getOrElse(IntConfig.PopulationSize, (int) sliderPopulationSize.getValue()));
    sliderGenotypeSize.valueProperty().set(options.getOrElse(IntConfig.GenotypeSize, (int) sliderGenotypeSize.getValue()));
    sliderGenerations.valueProperty().set(options.getOrElse(IntConfig.Limit, (int) sliderGenerations.getValue()));
  }

  /**
   * Öffnet einen Dialog zum Speichern der Algorithmus-Konfiguration
   */
  public void sendButton_saveConfig() {
    var fileChooser = new FileChooser();
    fileChooser.getExtensionFilters()
            .add(new FileChooser.ExtensionFilter("Genetischer Algorithmus (*.gacf)", "*.gacf"));

    var file = fileChooser.showSaveDialog(null);

    if (file != null) {
      try {
        createConfigOptions().serialize(file);
      } catch (Exception e) {
        DialogUtils.ShowAlert("Error", "Fehler beim Speichern der Datei!");
      }
    }
  }

  /**
   * Handlet die Rückwärtsnavigation
   */
  public void sendButton_backwards(){
    viewModel.navigation_Back();
  }

  @Override
  public void setViewModel(ViewModel viewModel){
    super.setViewModel(viewModel);
    setOptions(viewModel.getConfigOptions());
  }
}