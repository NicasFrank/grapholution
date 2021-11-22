package de.htwk.leipzig.grapholution.javafxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BestGenotype {

    private final StringProperty fitness = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();

    public BestGenotype(int fitness, int age) {

    }

}
