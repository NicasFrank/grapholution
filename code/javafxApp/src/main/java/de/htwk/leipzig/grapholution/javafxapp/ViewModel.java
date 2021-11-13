package de.htwk.leipzig.grapholution.javafxapp;


import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Random;

public class ViewModel {

  private final StringProperty inputField = new SimpleStringProperty();
  private final StringProperty outputField= new SimpleStringProperty();

  private final SceneControllerChoice sceneControllerChoice;

  private Hillclimber hilly;

  ViewModel(SceneControllerChoice sceneControllerChoice){
    this.sceneControllerChoice = sceneControllerChoice;
  }

  /**
   * switch anhand String je nach nächster Pane
   * @param nameOfNextScreen String mit Namen des nächsten Screens
   * @return bool ob laden erfolgreich oder nicht
   */
  public boolean navigation_configureScreen (Object nameOfNextScreen){
    boolean result=false;
    switch (nameOfNextScreen.toString()){
      case "Hillclimber":
        result = loadNewPane("ConfigHillclimber.fxml");
        break;

      case "Ein Anderer":
        //Something
        break;

      case "Auswertung":
        result = loadNewPane("AuswertungScreen.fxml");
        break;

      default:
        //Nichts
        break;
    }
    return result;
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
      Genotype<Boolean> genotype = hilly.run();
      EvoLibMapper evoLibMapper = new EvoLibMapper();

      BestGenotype bg = evoLibMapper.map(genotype);


      //outputField.set();
  }
  /**
   * versucht bestimmte Pane zu laden
   * @param paneName Name der zu ladenden Pane aus private String[] slides
   * @return wahr wenn erfolgreich false wenn nicht
   */
    private boolean loadNewPane (String paneName){
      FXMLLoader loader = new FXMLLoader(getClass().getResource(paneName));
      try {
        Pane nextPane = loader.load();
        SceneController newController = loader.getController();
        newController.setViewModel(this);
        return setNextScreen(nextPane);
      } catch (IOException e) {
        return false;
      }
    }

  /**
   * gibt nächste Scene an ersten Controller weiter
   * @param nextScreen nächste Scene als Pane
   * @return wahr wenn erfolgreich false wenn nicht
   */
    private boolean setNextScreen (Pane nextScreen){
      if(nextScreen!=null){
        return sceneControllerChoice.setNewScreen(nextScreen);
      } else {
        //Fehlermeldung
        return false;
      }
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
}
