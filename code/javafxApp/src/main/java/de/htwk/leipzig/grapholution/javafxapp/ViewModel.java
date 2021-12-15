package de.htwk.leipzig.grapholution.javafxapp;


import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import de.htwk.leipzig.grapholution.javafxapp.utils.DialogUtils;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.List;


public class ViewModel {

  private final StringProperty inputField = new SimpleStringProperty();
  private final StringProperty outputField= new SimpleStringProperty();

  private final SceneControllerBase sceneControllerBase;
  private Pane[] allScenes = new Pane[3];
  private int currentScene = -1;
  private boolean isAlgorithmStepByStep = false;

  private Hillclimber<Boolean> hilly;
  private ViewModelGeneticAlgorithm viewModelGeneticAlgorithm;

  ViewModel(SceneControllerBase sceneControllerBase, Pane firstPane){
    this.sceneControllerBase = sceneControllerBase;
    allScenes[0]=firstPane;
  }

  /**
   * switch anhand String je nach nächster Pane
   * @param nameOfNextScreen String mit Namen des nächsten Screens
   */
  public void navigation_configureScreen (EChoices nameOfNextScreen){
    currentScene++;
    switch (nameOfNextScreen) {
      case AlgorithmChoice :
        allScenes[0] = loadNewPane("AlgorithmChoice.fxml");
        break;
      case Hillclimber :
        allScenes[1] = loadNewPane("ConfigHillclimber.fxml");
        break;
      case GeneticAlgorithm :
        allScenes[1] = loadNewPane("ConfigGeneticAlgorithm.fxml");
        break;
      case ResultsHillclimber :
        climbTheHill(inputField.get());
        allScenes[2] = loadNewPane("ResultsHillclimber.fxml");
        outputField.set("Ergebnis");
        break;
      case ResultsGeneticAlgorithm :
        allScenes[2] = loadNewPane("ResultsGeneticAlgorithm.fxml");
        break;
      default :
        //handle das noch
        break;
    }
  }

  /**
   * dient zur Rückwärtsnavigation
   * anhand der aktuellen szene wird aus array die vorherige genommen
   */
  public void navigation_Back(){
    currentScene--;
    setNextScreen(allScenes[currentScene]);
  }

  /**
   * Hillclimber instanziert und ausgeführt
   * @param startConfig Startkonfiguration
   */
  public void climbTheHill(String startConfig){
      int genosize = 10;
      FitnessFunction<Boolean> fitnessfunctionO = new OneMaxEvaluator();
      Genotype<Boolean> genotypeO = new Genotype<>(Random::nextBoolean, fitnessfunctionO, genosize);
      Mutator<Boolean> mutatorS = new SwitchOneBit();

      hilly = new Hillclimber<>(genotypeO, mutatorS);

      hilly.run();
      EvoLibMapper evoLibMapper = new EvoLibMapper();

    List<BestGenotype> bg = evoLibMapper.map(hilly.getHistory());

      //outputField.set();
  }

  public void startGeneticAlgorithm(AlgorithmConfigOptions options){
    isAlgorithmStepByStep = options.getBool("isStepByStep");
    viewModelGeneticAlgorithm = new ViewModelGeneticAlgorithm(options
    );
  }

  public BestGenotype geneticAlgorithmNextStep(boolean untilDone){
    return viewModelGeneticAlgorithm.runAlgorithm(untilDone);
  }

    /**
   * versucht bestimmte Pane zu laden
   * @param paneName Name der zu ladenden Pane aus private String[] slides
   * @return wahr wenn erfolgreich false wenn nicht
   */
    private Pane loadNewPane (String paneName){
      FXMLLoader loader = new FXMLLoader(getClass().getResource(paneName));
      try {
        Pane nextPane = loader.load();
        SceneController newController = loader.getController();
        newController.setViewModel(this);
        setNextScreen(nextPane);
        return nextPane;
      } catch (IOException e) {
        return null;
      }
    }

  /**
   * gibt nächste Scene an ersten Controller weiter
   * @param nextScreen nächste Scene als Pane
   */
    private void setNextScreen (Pane nextScreen){
        sceneControllerBase.setNewScreen(nextScreen);
    }

  /**
   * Handhabung des Eingabefeldes:
   * iteriert durch das Eingabefeld und speichert in einem char Array
   * @return true falls Eingabe 0 oder 1 (Buchstabe)
   */
  private boolean isInputCorrect(){
        char[] input = inputField.get().toCharArray();
        for (char c : input) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * Methoden geben Eingabe und Ausgabefeld zurück
     **/
    public Property<String> outputFieldProperty() {
        return outputField;
    }
  public Property<String> inputFieldProperty() {
    return inputField;
  }
  public boolean getIsAlgorithmStepByStep(){return isAlgorithmStepByStep;}
}
