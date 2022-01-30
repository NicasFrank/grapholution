package de.htwk.leipzig.grapholution.javafxapp;


import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.GenModel;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;


public class ViewModel {
    public static Color FITNESS_CHART_COLOR = Color.DARKBLUE;
    public static Color AGE_CHART_COLOR = Color.LIMEGREEN;
    public static Color GOODNESS_CHART_COLOR = Color.CYAN;

    private final Stack<Scene> scenes = new Stack<>();
    private final Stage stage;
    private boolean isAlgorithmStepByStep = false;

    private AlgorithmConfigOptions configOptions = new AlgorithmConfigOptions();
    private ViewModelGeneticAlgorithm viewModelGeneticAlgorithm;
    private ViewModelHillclimber viewModelHillclimber;

    public ViewModel(Stage stage, Scene firstScene) {
        this.stage = stage;
        scenes.add(firstScene);
    }

  /**
   * switch anhand String je nach nächster Pane
   * @param nameOfNextScreen String mit Namen des nächsten Screens
   */
  public void navigation_configureScreen (EChoices nameOfNextScreen){
    switch (nameOfNextScreen) {
      case AlgorithmChoice :
        scenes.add(loadNewPane("AlgorithmChoice.fxml"));
        break;
      case Hillclimber :
          scenes.add(loadNewPane("ConfigHillclimber.fxml"));
        break;
      case GeneticAlgorithm :
          scenes.add(loadNewPane("ConfigGeneticAlgorithm.fxml"));
        break;
      case ResultsHillclimber :
          scenes.add(loadNewPane("ResultsHillclimber.fxml"));
        break;
      case ResultsGeneticAlgorithm :
          scenes.add(loadNewPane("ResultsGeneticAlgorithm.fxml"));
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
    public void navigation_Back() {
        scenes.pop();
        setNextScreen(scenes.peek());
    }

    /**
     * Hillclimber instanziert und ausgeführt
     *
     */

    public void startHillclimberAlgorithm(AlgorithmConfigOptions options,SceneControllerHillclimber sceneControllerHillclimber) {
        setConfigOptions(options);
        viewModelHillclimber = new ViewModelHillclimber(options,sceneControllerHillclimber);
    }

    public List<HillModel> runHillclimberAlgorithm(){
      return viewModelHillclimber.runAlgorithm();
    }

    public GenModel geneticAlgorithmNextStep() {
        return viewModelGeneticAlgorithm.runAlgorithm();
    }

  public List<GenModel> geneticAlgorithmUntilDone(){
    return viewModelGeneticAlgorithm.finishAlgorithm();
  }

  public void startGeneticAlgorithm(AlgorithmConfigOptions options){
    setConfigOptions(options);
    isAlgorithmStepByStep = options.getBool(BoolConfig.IsStepByStep);
    viewModelGeneticAlgorithm = new ViewModelGeneticAlgorithm(options);
  }

    /**
     * versucht bestimmte Pane zu laden
     *
     * @param paneName Name der zu ladenden Pane aus private String[] slides
     * @return wahr wenn erfolgreich false wenn nicht
     */
    private Scene loadNewPane(String paneName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(paneName));
        try {
            Pane nextPane = loader.load();
            SceneController newController = loader.getController();
            newController.setViewModel(this);
            var nextScene = new Scene(nextPane);
            nextScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles_new.css")).toExternalForm());
            setNextScreen(nextScene);
            return nextScene;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * gibt nächste Scene an die Stage weiter
     *
     * @param nextScene nächste Scene
     */
    private void setNextScreen(Scene nextScene) {
        stage.setScene(nextScene);
    }


    /**
     * Methoden geben Eingabe und Ausgabefeld zurück
     **/
    public StringProperty VMoutputFieldProperty() {
        return viewModelHillclimber.outputFieldProperty();
    }
  public StringProperty VMinputFieldProperty() {
    return viewModelHillclimber.inputFieldProperty();
  }
  public boolean getIsAlgorithmStepByStep(){return isAlgorithmStepByStep;}

  public AlgorithmConfigOptions getConfigOptions() {
    return configOptions;
  }

  public Statistics<Boolean> getHillclimberStatistics(){
      return viewModelHillclimber.getHillclimberStatistic();
  }

  public Statistics<Boolean> getGeneticAlgorithmStatistics(){
      return viewModelGeneticAlgorithm.getGeneticAlgorithmStatistic();
  }

  public void setConfigOptions(AlgorithmConfigOptions configOptions) {
    this.configOptions = configOptions;
  }
}
