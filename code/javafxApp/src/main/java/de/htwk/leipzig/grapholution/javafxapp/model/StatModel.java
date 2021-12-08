package de.htwk.leipzig.grapholution.javafxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatModel {

    private final StringProperty duration;
    private final StringProperty fitness;

    public StatModel(int duration, int fitness) {
        this.duration = new SimpleStringProperty(String.valueOf(duration));
        this.fitness = new SimpleStringProperty(String.valueOf(fitness));
    }
}
