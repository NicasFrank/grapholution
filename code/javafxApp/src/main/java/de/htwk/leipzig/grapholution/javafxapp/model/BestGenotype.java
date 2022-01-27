package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model-Klasse um besten Genotypen, mit Property-Variablen
 */

public class BestGenotype {

    private final SimpleStringProperty fitness = new SimpleStringProperty();
    private final SimpleStringProperty age = new SimpleStringProperty();

    public BestGenotype(int fitness, int age) {
        this.fitness.setValue(String.valueOf(fitness));
        this.age.setValue(String.valueOf(age));
    }
    public BestGenotype (Genotype<Boolean> genotype){
        this.fitness.setValue(String.valueOf(genotype.getFitness()));
        this.age.setValue(String.valueOf(genotype.getAge()));
    }
    public SimpleStringProperty fitnessPropertyIndividual(){return fitness;}
    public SimpleStringProperty ageProperty(){return age;}
}
