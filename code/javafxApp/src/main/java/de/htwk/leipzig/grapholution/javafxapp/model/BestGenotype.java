package de.htwk.leipzig.grapholution.javafxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BestGenotype {

    private final SimpleStringProperty fitness = new SimpleStringProperty();
    private final SimpleStringProperty age = new SimpleStringProperty();

    public BestGenotype(int fitness, int age) {
        this.fitness.setValue(String.valueOf(fitness));
        this.age.setValue(String.valueOf(age));
    }
    public SimpleStringProperty getFitness(){return fitness;}
    public SimpleStringProperty getAge(){return age;}
}
