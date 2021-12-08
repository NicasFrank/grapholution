package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatModel extends Statistics {

    private final StringProperty fitness;

    private ObservableList<StatModel> historyData;

    public StatModel(int fitness) {
        super();
        historyData = FXCollections.observableArrayList();
        this.fitness = new SimpleStringProperty(String.valueOf(fitness));
        historyData.add(new StatModel(fitness));
    }

    public ObservableList<StatModel> getHistoryData() {
        return historyData;
    }

    public void setHistoryData(ObservableList<StatModel> historyData) {
        this.historyData = historyData;
    }
}
