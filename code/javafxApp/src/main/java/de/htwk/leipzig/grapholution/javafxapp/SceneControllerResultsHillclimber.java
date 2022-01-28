package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Scene Kontroller Klasse für Hillclimber Statistik und Visualisierung
 */
public class SceneControllerResultsHillclimber extends SceneController implements Initializable{

    @FXML
    private TextField outputField;
    @FXML
    private TableView<HillModel> tableViewResults;
    @FXML
    private LineChart<Integer, Number> lineChartResults;
    @FXML
    private TableColumn<HillModel, String> iteration;
    @FXML
    private TableColumn<HillModel, String> fitness;
    @FXML
    private TableColumn<HillModel, String> age;

    private ViewModel viewModel;
    private final ObservableList<HillModel> allResults = FXCollections.observableArrayList();
    private LineChartHandler lineChartHandler;

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        lineChartHandler = new LineChartHandler(
                lineChartResults,
                List.of("Fitness", "Alter"),
                List.of(ViewModel.FITNESS_CHART_COLOR, ViewModel.AGE_CHART_COLOR)
        );
        allResults.addAll(viewModel.runHillclimberAlgorithm());
        outputField.textProperty().bindBidirectional(viewModel.VMoutputFieldProperty());
        setResults();
    }

    public void initialize(URL location, ResourceBundle resources){
        iteration.setCellValueFactory((cellData -> cellData.getValue().getIteration()));
        fitness.setCellValueFactory((cellData -> cellData.getValue().getFitness()));
        age.setCellValueFactory((cellData -> cellData.getValue().getAge()));
    }

    /**
     * Methode um besten Fitnesswert des Hillclimber Algorithmus, in TableView zu setzen
     */

    public void setResults() {
        tableViewResults.setItems(allResults);
        setLineChartResults();
    }

    /**
     * Methode um zeitlichen Verlauf der Fitnesswerte in LinienChart zu setzen
     */
    public void setLineChartResults() {
        var statistics = viewModel.getHillclimberStatistics();

        statistics.getBestIndividuals().stream()
                .map(Genotype::getFitness)
                .forEach(i -> lineChartHandler.addData(i, "Fitness"));
        statistics.getBestIndividuals().stream()
                .map(Genotype::getAge)
                .forEach(i -> lineChartHandler.addData(i, "Alter"));
    }

    /**
     * Handled die Rückwärtsnavigation
     */
    public void sendButton_backwards(){
        viewModel.navigation_Back();
    }
}