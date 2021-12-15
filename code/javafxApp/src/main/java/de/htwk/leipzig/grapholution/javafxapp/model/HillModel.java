package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;
import javafx.beans.property.SimpleStringProperty;

/**
 *  Klasse um HillclimberModel
 */

public class HillModel extends Statistics {
    /**
     * SimpleStringProperty Variable fitness
     */
    private final SimpleStringProperty fitness;

    /**
     * Konstruktor
     * @param fitness
     */
    public HillModel(int fitness) {
        super();
        this.fitness = new SimpleStringProperty(String.valueOf(fitness));
    }

    /**
     * getter für fitness
     * @return
     */
    public String getFitness(){return fitness.get();}
}
