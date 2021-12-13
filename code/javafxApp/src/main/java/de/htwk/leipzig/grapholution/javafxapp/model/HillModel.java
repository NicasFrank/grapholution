package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import javafx.beans.property.SimpleStringProperty;

public class HillModel extends Statistics {

    private final SimpleStringProperty fitness;


    public HillModel(int fitness) {
        super();
        this.fitness = new SimpleStringProperty(String.valueOf(fitness));
    }
    
    public String getFitness(){return fitness.get();}
}
