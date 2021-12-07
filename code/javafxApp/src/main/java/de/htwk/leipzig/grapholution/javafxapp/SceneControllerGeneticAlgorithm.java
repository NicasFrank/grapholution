package de.htwk.leipzig.grapholution.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SceneControllerGeneticAlgorithm extends SceneController{

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
    viewModel.navigation_configureScreen("AuswertungGeneticAlgorithm");
    viewModel.startGeneticAlgorithm(
        checkBoxStepByStep.isSelected(),
        radioMutationBinary.isSelected(),
        radioMutationBinary.isSelected() ? sliderMutationChance.getValue() : 0,
        radioFitnessOneMax.isSelected(),
        sliderRecombinationChance.getValue(),
        sliderPopulationSize.getValue(),
        sliderGenotypeSize.getValue(),
        sliderGenerations.getValue());
  }

  public void sendButton_backwards(){
    viewModel.navigation_Back();
  }

  public void setViewModel(ViewModel viewModel){
    this.viewModel=viewModel;
  }
}