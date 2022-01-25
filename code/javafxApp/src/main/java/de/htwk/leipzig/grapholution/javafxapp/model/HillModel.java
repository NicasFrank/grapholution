package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  Klasse um HillclimberModel
 */

public class HillModel {
    /**
     * SimpleStringProperty Variable fitness
     */
    private final SimpleStringProperty fitness;
    private final SimpleStringProperty iteration;
    private final SimpleStringProperty age;

    /**
     * Konstruktor
     * @param fitness
     */
    public HillModel(int fitness) {
        this.fitness = new SimpleStringProperty(String.valueOf(fitness));
        this.iteration = new SimpleStringProperty("1");
        this.age = new SimpleStringProperty("0");
    }

    /**
     * @TODO iteration
     * @param genotype
     */
    public HillModel(Genotype genotype){
        this.fitness = new SimpleStringProperty(String.valueOf(genotype.getFitness()));
        this.age = new SimpleStringProperty(String.valueOf(genotype.getAge()));
        this.iteration = new SimpleStringProperty(String.valueOf(genotype.getAge()));
    }

    /**
     * getter für fitness
     * @return
     */
    public StringProperty getFitness(){return fitness;}
    public StringProperty getIteration(){return iteration;}
    public StringProperty getAge(){return age;}
}
