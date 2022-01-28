package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.javafxapp.model.HillModel;
import de.htwk.leipzig.grapholution.javafxapp.utils.LineChartUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
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
    private LineChart<Integer, Integer> lineChartResults;
    @FXML
    private TableColumn<HillModel, String> iteration;
    @FXML
    private TableColumn<HillModel, String> fitness;
    @FXML
    private TableColumn<HillModel, String> age;

    private ViewModel viewModel;
    private final ObservableList<HillModel> allResults = FXCollections.observableArrayList();

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        allResults.addAll(viewModel.runHillclimberAlgorithm());
        outputField.textProperty().bindBidirectional(viewModel.VMoutputFieldProperty());
        setTableViewResults();
    }

    public void initialize(URL location, ResourceBundle resources){
        iteration.setCellValueFactory((cellData -> cellData.getValue().getIteration()));
        fitness.setCellValueFactory((cellData -> cellData.getValue().getFitness()));
        age.setCellValueFactory((cellData -> cellData.getValue().getAge()));
    }

    /**
     * Methode um besten Fitnesswert des Hillclimber Algorithmus, in TableView zu setzen
     */

    public void setTableViewResults() {
        tableViewResults.setItems(allResults);
        setLineChartResults();
    }

    /**
     * Methode um zeitlichen Verlauf der Fitnesswerte in LinienChart zu setzen
     */
    public void setLineChartResults() {
        var statistics = viewModel.getHillclimberStatistics();

        LineChartUtils.addDataSeries(
                lineChartResults,
                statistics.getBestIndividuals().stream()
                        .map(Genotype::getFitness)
                        .collect(Collectors.toList()),
                "Fitness",
                Color.DARKBLUE);
        LineChartUtils.addDataSeries(
                lineChartResults,
                statistics.getBestIndividuals().stream()
                        .map(Genotype::getAge)
                        .collect(Collectors.toList()),
                "Alter",
                Color.LIMEGREEN);
        LineChartUtils.setLegendColors(lineChartResults, List.of(Color.DARKBLUE, Color.LIMEGREEN));
    }

    /**
     * Handled die Rückwärtsnavigation
     */
    public void sendButton_backwards(){
        viewModel.navigation_Back();
    }
}