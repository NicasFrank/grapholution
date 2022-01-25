package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.model.GenModel;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    TableView<GenModel> tableViewResults;
    @FXML
    TableColumn<GenModel, String> duration;
    @FXML
    TableColumn<GenModel, String> fitnessPopulation;
    @FXML
    TableColumn<GenModel, String> fitnessBestIndividual;
    @FXML
    TableColumn<GenModel, String> age;
    @FXML
    private Button buttonNextStep,buttonFastForward;

    private ViewModel viewModel;
    private final ObservableList<GenModel> allResults = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        duration.setCellValueFactory((cellData -> cellData.getValue().iterationProperty()));
        age.setCellValueFactory((cellData -> cellData.getValue().ageProperty()));
        fitnessBestIndividual.setCellValueFactory((cellData -> cellData.getValue().fitnessIndividualProperty()));
        fitnessPopulation.setCellValueFactory((cellData -> cellData.getValue().fitnessPopulationProperty()));
    }

    public void sendButton_backwards(){
      viewModel.navigation_Back();
    }

    public void nextStep(){
        allResults.add(viewModel.geneticAlgorithmNextStep());
        tableViewResults.setItems(allResults);
    }

    /**
     * @TODO irgendwie funktioniert hierbei fitness des individuum nicht
     */
    public void fastForward(){
        allResults.removeAll();
        tableViewResults.getItems().clear();
        allResults.addAll(viewModel.geneticAlgorithmUntilDone());
        buttonFastForward.setDisable(true);
        buttonNextStep.setDisable(true);
        tableViewResults.setItems(allResults);
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
