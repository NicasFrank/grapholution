package de.htwk.leipzig.grapholution.javafxapp.models;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse zur tabellarischen Darstellung des Verlaufs des Hillclimbers
 */
public class HillModel {
    private final SimpleStringProperty fitness;
    private final SimpleStringProperty iteration;
    private final SimpleStringProperty age;

    /**
     * Erstellt ein neues HillModel aus einem Genotyp.
     * @param iteration Die Iteration des Algorithmus, an der der Genotyp entstanden ist
     * @param genotype Der Genotyp, aus dem das HillModel erstellt wird
     */
    public HillModel(int iteration, Genotype<Boolean> genotype){
        this.fitness = new SimpleStringProperty(String.valueOf(genotype.getFitness()));
        this.age = new SimpleStringProperty(String.valueOf(genotype.getAge()));
        this.iteration = new SimpleStringProperty(String.valueOf(iteration));
    }

    public StringProperty getFitness(){return fitness;}
    public StringProperty getIteration(){return iteration;}
    public StringProperty getAge(){return age;}
}
