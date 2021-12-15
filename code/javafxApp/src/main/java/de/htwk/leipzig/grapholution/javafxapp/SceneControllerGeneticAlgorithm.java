package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

public class SceneControllerGeneticAlgorithm extends SceneController{

  public RadioButton radioFitnessZeroMax;
  @FXML
  private RadioButton radioMutationBinary,radioFitnessOneMax;
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

  private ViewModel viewModel;

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
            .add("isStepByStep", checkBoxStepByStep.isSelected())
            .add("mutationIsBinary", radioMutationBinary.isSelected())
            .add("mutationChance", radioMutationBinary.isSelected() ? (int) sliderMutationChance.getValue() : 0)
            .add("fitnessIsOneMax", radioFitnessOneMax.isSelected())
            .add("recombinationChance", sliderRecombinationChance.getValue())
            .add("populationSize", (int) sliderPopulationSize.getValue())
            .add("genotypeSize", (int) sliderGenotypeSize.getValue())
            .add("generationAmount", (int) sliderGenerations.getValue());
  }

  private void setOptions(AlgorithmConfigOptions options) {
    checkBoxStepByStep.selectedProperty().set(options.getOrElse("isStepByStep", checkBoxStepByStep.isSelected()));
    radioMutationBinary.selectedProperty().set(options.getOrElse("mutationIsBinary", radioMutationBinary.isSelected()));
    sliderMutationChance.valueProperty().set(options.getOrElse("mutationChance", sliderMutationChance.getValue()));
    sliderRecombinationChance.valueProperty().set(options.getOrElse("recombinationChance", sliderRecombinationChance.getValue()));
    sliderPopulationSize.valueProperty().set(options.getOrElse("populationSize", sliderPopulationSize.getValue()));
    sliderGenotypeSize.valueProperty().set(options.getOrElse("genotypeSize", sliderGenotypeSize.getValue()));
    sliderGenerations.valueProperty().set(options.getOrElse("generationAmount", sliderGenerations.getValue()));

    if (options.getOrElse("fitnessIsOneMax", radioFitnessOneMax.isSelected())) {
      radioFitnessOneMax.selectedProperty().set(true);
    } else {
      radioFitnessZeroMax.selectedProperty().set(true);
    }
  }

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

  public void sendButton_backwards(){
    viewModel.navigation_Back();
  }

  public void setViewModel(ViewModel viewModel){
    this.viewModel=viewModel;
    setOptions(viewModel.getConfigOptions());
  }
}