package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.HistoryResults;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneControllerResultsGeneticAlgorithm extends SceneController implements Initializable {

    @FXML
    TableView<HistoryResults> tableViewResults;
    @FXML
    TableColumn<HistoryResults, String> population;
    @FXML
    TableColumn<HistoryResults, String> duration;
    @FXML
    TableColumn<HistoryResults, String> fitness;
    @FXML
    private Button buttonNextStep,buttonFastForward;

    private ViewModel viewModel;

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
      this.viewModel = viewModel;
    }

    public void loadButtonTableView(ActionEvent e) {
    }

    public void loadButtonLineChart(ActionEvent e) {
    }

    public void sendButton_backwards(){
      viewModel.navigation_Back();
    }

    public void nextStep(){
      BestGenotype bestGenotype = viewModel.geneticAlgorithmNextStep(false);
    }
    public void fastForward(){
      BestGenotype bestGenotype = viewModel.geneticAlgorithmNextStep(true);
      buttonFastForward.setDisable(true);
      buttonNextStep.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      duration.setCellValueFactory((cellData -> cellData.getValue().durationProperty()));
      population.setCellValueFactory((cellData -> cellData.getValue().populationProperty()));
      fitness.setCellValueFactory((cellData -> cellData.getValue().fitnessProperty()));
    }
}
