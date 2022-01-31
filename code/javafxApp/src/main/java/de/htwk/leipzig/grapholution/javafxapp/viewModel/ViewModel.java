package de.htwk.leipzig.grapholution.javafxapp.viewModel;


import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.BoolConfig;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.MainApp;
import de.htwk.leipzig.grapholution.javafxapp.enums.EChoices;
import de.htwk.leipzig.grapholution.javafxapp.sceneController.SceneController;
import de.htwk.leipzig.grapholution.javafxapp.models.GenModel;
import de.htwk.leipzig.grapholution.javafxapp.models.HillModel;
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


/**
 * ViewModel-Klasse
 */
public class ViewModel {
    public static Color FITNESS_CHART_COLOR = Color.DARKBLUE;
    public static Color AGE_CHART_COLOR = Color.LIMEGREEN;
    public static Color GOODNESS_CHART_COLOR = Color.CYAN;

    private final Stack<Scene> scenes = new Stack<>();
    private final Stage stage;
    private boolean isAlgorithmStepByStep = false;
    private boolean problemIsOneMax = false;

    private AlgorithmConfigOptions configOptions = new AlgorithmConfigOptions();
    private ViewModelGeneticAlgorithm viewModelGeneticAlgorithm;
    private ViewModelHillclimber viewModelHillclimber;

    /**
     * Konstruktor
     * @param stage Stage des Fensters
     * @param firstScene Scene des ersten Fensters
     */
    public ViewModel(Stage stage, Scene firstScene) {
        this.stage = stage;
        scenes.add(firstScene);
    }

  /**
   * Wechsel zum nächsten Fenster, passend zum übergebenen Enum-Wert
   * @param nameOfNextScreen Enum-Wert mit Namen des nächsten Fensters
   */
  public void navigation_configureScreen(EChoices nameOfNextScreen){
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
     * Wechselt zum vorherigen Fenster
     */
    public void navigation_Back() {
        scenes.pop();
        setNextScreen(scenes.peek());
    }

    /**
     * Hillclimber instanziert
     * @param options Die Einstellungswerte, mit den der Hillclimber ausgeführt werden soll
     * @param input Die Werte des Genotypen, mit dem der Hillclimber starten soll
     */
    public void startHillclimberAlgorithm(AlgorithmConfigOptions options, String input) {
        setConfigOptions(options);
        viewModelHillclimber = new ViewModelHillclimber(options, input);
    }

    /**
     * Startet den Hillclimber und gibt dessen Ergebnis zurück
     * @return Das Ergebnis der Ausführung des Hillclimbers
     */
    public List<HillModel> runHillclimberAlgorithm(){
      return viewModelHillclimber.runAlgorithm();
    }

    /**
     * Führt einen Schritt des genetischen Algorithmus aus und gibt das Ergebnis zurück
     * @return Das Ergebnis der Ausführung des Schrittes
     */
    public GenModel geneticAlgorithmNextStep() {
        return viewModelGeneticAlgorithm.runAlgorithm();
    }

    /**
     * Führt den genetischen Algorithmus bis zum Ende aus und gibt das Ergebnis zurück
     * @return Das Ergebnis der Ausführung des genetischen Algorithmus
     */
  public List<GenModel> geneticAlgorithmUntilDone(){
    return viewModelGeneticAlgorithm.finishAlgorithm();
  }

    /**
     * Initialisiert und startet den genetischen Algorithmus mit den übergebenen Einstellungswerten
     * @param options Die Einstellungswerte, mit den der genetische Algorithmus initialisiert werden soll
     */
  public void startGeneticAlgorithm(AlgorithmConfigOptions options){
    setConfigOptions(options);
    isAlgorithmStepByStep = options.getBool(BoolConfig.IsStepByStep);
    viewModelGeneticAlgorithm = new ViewModelGeneticAlgorithm(options);
  }

    /**
     * Lädt das Fenster mit dem übergebenen Namen in eine neue Scene und gibt diese zurück
     * @param paneName Name des zu ladenden Fensters
     * @return Die Scene des Fensters
     */
    private Scene loadNewPane(String paneName) {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(paneName));
        try {
            Pane nextPane = loader.load();
            SceneController newController = loader.getController();
            newController.setViewModel(this);
            var nextScene = new Scene(nextPane);
            nextScene.getStylesheets().add(Objects.requireNonNull(MainApp.class.getResource("styles_new.css")).toExternalForm());
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
        stage.centerOnScreen();
    }


    /**
     * Gibt das Ausgabefeld zurück
     * @return Das Ausgabefeld
     **/
    public StringProperty VMoutputFieldProperty() {
        return viewModelHillclimber.outputFieldProperty();
    }

    /**
     * Gibt zurück, ob der genetische Algorithmus Schritt für Schritt ausgeführt werden kann
     * @return Ob der genetische Algorithmus Schritt für Schritt ausgeführt werden kann
     */
    public boolean getIsAlgorithmStepByStep(){return isAlgorithmStepByStep;}

    /**
     * Gibt die Einstellungswerte zurück
     * @return Die Einstellungswerte
     */
    public AlgorithmConfigOptions getConfigOptions() {
    return configOptions;
  }

    /**
     * Gibt die Statistiken des Hillclimbers zurück
     * @return Die Statistiken des Hillclimbers
     */
    public Statistics<Boolean> getHillclimberStatistics(){
      return viewModelHillclimber.getHillclimberStatistic();
  }

    /**
     * Gibt die Statistiken des genetischen Algorithmus zurück
     * @return Die Statistiken des genetischen Algorithmus
     */
    public Statistics<Boolean> getGeneticAlgorithmStatistics(){
        return viewModelGeneticAlgorithm.getGeneticAlgorithmStatistic();
    }

    /**
     * Setzt die Einstellungswerte
     * @param configOptions Die zu setzenden Einstellungswerte
     */
    public void setConfigOptions(AlgorithmConfigOptions configOptions) {
        problemIsOneMax = configOptions.getOrElse(BoolConfig.FitnessIsOneMax, true);
        this.configOptions = configOptions;
    }

    /**
     * Gibt zurück, ob es sich bei dem zu lösenden Problem um das One-Max-Problem handelt
     * @return Ob es sich bei dem zu lösenden Problem um das One-Max-Problem handelt
     */
    public boolean getProblemIsOneMax() {
        return problemIsOneMax;
    }

    /**
     * Setzt, ob es sich bei dem zu lösenden Problem um das One-Max-Problem handelt
     * @param problemIsOneMax Ob es sich bei dem zu lösenden Problem um das One-Max-Problem handelt
     */
    public void setProblemIsOneMax(boolean problemIsOneMax) {
        this.problemIsOneMax = problemIsOneMax;
    }

    /**
     * Gibt die Stage des Fensters zurück
     * @return Die Stage des Fensters
     */
    public Stage getStage() {
        return stage;
    }
}
