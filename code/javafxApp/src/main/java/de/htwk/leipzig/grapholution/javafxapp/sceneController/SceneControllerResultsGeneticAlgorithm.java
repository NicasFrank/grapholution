package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneControllerResultsGeneticAlgorithm extends SceneController implements Initializable {

    @FXML
    TableView<Statistics> tableViewResults;
    @FXML
    TableColumn<HillModel, String> fitness;
    @FXML
    private Button buttonNextStep,buttonFastForward;

    private ViewModel viewModel;

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

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        if (viewModel.getIsAlgorithmStepByStep()) {
            buttonFastForward.setDisable(false);
            buttonNextStep.setDisable(false);
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        fitness.setCellValueFactory(new PropertyValueFactory<HillModel, String>("fitness"));
    }
}
