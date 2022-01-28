package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.statistics.ColorBitString;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import de.htwk.leipzig.grapholution.javafxapp.model.GenModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SceneControllerResultsGeneticAlgorithm extends SceneController implements Initializable {

    @FXML
    Pagination bitStringPagination;
    @FXML
    TableView<GenModel> tableViewResults;
    @FXML
    TableColumn<GenModel, String> duration;
    @FXML
    TableColumn<GenModel, String> fitnessPopulation;
    @FXML
    TableColumn<GenModel, String> fitnessBestIndividual;
    @FXML
    TableColumn<GenModel, String> bitsBestIndividual;
    @FXML
    TableColumn<GenModel, String> age;
    @FXML
    private LineChart<Integer, Number> lineChartResults;
    @FXML
    private Button buttonNextStep,buttonFastForward;

    private ViewModel viewModel;
    private final ObservableList<GenModel> allResults = FXCollections.observableArrayList();
    private LineChartHandler lineChartHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bitsBestIndividual.setCellValueFactory((cellData -> cellData.getValue().bitsProperty()));
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

        addLineChartValues(viewModel.getGeneticAlgorithmStatistics());
        paintBitStrings();
    }

    private void addLineChartValues(Statistics<Boolean> statistics) {
        var history = statistics.getHistory();
        var lastPopulation = history.get(history.size() - 1);

        lineChartHandler.addData(lastPopulation.getBestIndividual().getFitness(), "Fitness");
        lineChartHandler.addData(lastPopulation.getBestIndividual().getAge(), "Alter");
        lineChartHandler.addData(lastPopulation.getGoodness(), "Guete");
    }

    /**
     *
     */
    public void fastForward(){
        var currentIteration = allResults.size();
        allResults.addAll(viewModel.geneticAlgorithmUntilDone()
                .stream()
                .skip(currentIteration)
                .collect(Collectors.toList()));
        buttonFastForward.setDisable(true);
        buttonNextStep.setDisable(true);
        tableViewResults.setItems(allResults);

        var statistics = viewModel.getGeneticAlgorithmStatistics();

        statistics.getBestIndividuals().stream()
                .skip(currentIteration)
                .map(Genotype::getFitness)
                .forEach(i -> lineChartHandler.addData(i, "Fitness"));
        statistics.getBestIndividuals().stream()
                .skip(currentIteration)
                .map(Genotype::getAge)
                .forEach(i -> lineChartHandler.addData(i, "Alter"));
        statistics.getHistory().stream()
                .skip(currentIteration)
                .map(Population::getGoodness)
                .forEach(i -> lineChartHandler.addData(i, "Guete"));

        paintBitStrings();
    }

    /**
     * setter für viewmodel und bindet outputfield an output vom viewmodel
     *
     * @param viewModel gleiche ViewModel für alle
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;

        lineChartHandler = new LineChartHandler(
                lineChartResults, List.of("Fitness", "Alter", "Guete"),
                List.of(ViewModel.FITNESS_CHART_COLOR, ViewModel.AGE_CHART_COLOR, ViewModel.GOODNESS_CHART_COLOR));

        var statistics = viewModel.getGeneticAlgorithmStatistics();
        var bestIndividuals = statistics.getBestIndividuals();
        allResults.add(new GenModel(0,
                bestIndividuals.get(bestIndividuals.size() - 1),
                statistics.getHistory().get(bestIndividuals.size()-1)));
        addLineChartValues(statistics);
        paintBitStrings();

        if(viewModel.getIsAlgorithmStepByStep()){
            buttonFastForward.setDisable(false);
            buttonNextStep.setDisable(false);
        } else {
            fastForward();
        }
    }

    private void paintBitStrings() {
        final var pageSize = 50;
        var bitStrings = viewModel.getGeneticAlgorithmStatistics().getColorBitStrings();
        bitStringPagination.setPageCount((int) Math.ceil(1.0 * bitStrings.size() / pageSize));
        bitStringPagination.setPageFactory(i -> {
            var scrollPane = new ScrollPane();

            var canvas = new Canvas();
            canvas.setWidth(bitStringPagination.getPrefWidth() * 0.95);
            scrollPane.setContent(canvas);
            final var pageStart = i * pageSize;
            CanvasPainter.paintBitStrings(canvas, bitStrings.subList(pageStart, Math.min(pageStart + pageSize, bitStrings.size())));

            return scrollPane;
        });
    }
}
