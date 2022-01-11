package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class SCResultsGeneticAlgorithm extends SceneController implements Initializable {

    @FXML
    TableView<TableModel> tableViewResults;
    @FXML
    TableColumn<TableModel, String> duration;
    @FXML
    TableColumn<TableModel, String> fitnessPopulation;
    @FXML
    TableColumn<TableModel, String> fitnessBestIndividual;
    @FXML
    TableColumn<TableModel, String> age;
    @FXML
    private Button buttonNextStep,buttonFastForward;

    private ViewModel viewModel;
    private final Statistics<Boolean> stats = new Statistics<>();
    private final ObservableList<TableModel> allResults = FXCollections.observableArrayList();

    public void sendButton_backwards(){
      viewModel.navigation_Back();
    }

    public void nextStep(){
      allResults.add(viewModel.geneticAlgorithmNextStep());
      tableViewResults.setItems(allResults);
    }
    public void fastForward(){
      allResults.removeAll();
      allResults.addAll(viewModel.geneticAlgorithmUntilDone());
      buttonFastForward.setDisable(true);
      buttonNextStep.setDisable(true);
      tableViewResults.setItems(allResults);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      duration.setCellValueFactory((cellData -> cellData.getValue().iterationProperty()));
      age.setCellValueFactory((cellData -> cellData.getValue().ageProperty()));
      fitnessBestIndividual.setCellValueFactory((cellData -> cellData.getValue().fitnessIndividualProperty()));
      //fitnessPopulation.setCellValueFactory((cellData -> cellData.getValue().fitnessPopulationProperty()));
    }
    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
      this.viewModel = viewModel;
      if(viewModel.getIsAlgorithmStepByStep()){
        buttonFastForward.setDisable(false);
        buttonNextStep.setDisable(false);
      } else {
        fastForward();
      }
    }
}
