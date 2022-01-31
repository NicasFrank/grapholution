package de.htwk.leipzig.grapholution.javafxapp.sceneController;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.javafxapp.handlers.LineChartHandler;
import de.htwk.leipzig.grapholution.javafxapp.models.HillModel;
import de.htwk.leipzig.grapholution.javafxapp.viewModel.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * SceneController für das Fenster zur Auswertung des Hillclimbers
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

    private final ObservableList<HillModel> allResults = FXCollections.observableArrayList();
    private LineChartHandler lineChartHandler;

    @Override
    public void setViewModel(ViewModel viewModel) {
        super.setViewModel(viewModel);
        lineChartHandler = new LineChartHandler(
                lineChartResults,
                List.of("Fitness", "Alter"),
                List.of(ViewModel.FITNESS_CHART_COLOR, ViewModel.AGE_CHART_COLOR)
        );
        allResults.addAll(viewModel.runHillclimberAlgorithm());
        outputField.textProperty().bindBidirectional(viewModel.VMoutputFieldProperty());
        setResults();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        iteration.setCellValueFactory((cellData -> cellData.getValue().getIteration()));
        fitness.setCellValueFactory((cellData -> cellData.getValue().getFitness()));
        age.setCellValueFactory((cellData -> cellData.getValue().getAge()));
    }

    /**
     * Methode um besten Fitnesswert des Hillclimber Algorithmus, in TableView zu setzen und um das Diagramm zu zeichnen
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