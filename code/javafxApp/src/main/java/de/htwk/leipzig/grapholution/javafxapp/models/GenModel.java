package de.htwk.leipzig.grapholution.javafxapp.models;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ModelKlasse GenerischeModels
 */

public class GenModel {

    private final SimpleStringProperty fitnessIndividual;
    private final SimpleStringProperty age;
    private final SimpleStringProperty iteration;
    private final SimpleStringProperty fitnessPopulation;
    private final SimpleStringProperty bits;

    public GenModel(int iteration, Genotype<Boolean> genotype, Population<Boolean> population) {
        this.fitnessIndividual = new SimpleStringProperty(String.valueOf(genotype.getFitness()));
        this.age = new SimpleStringProperty(String.valueOf(genotype.getAge()));
        this.iteration = new SimpleStringProperty(String.valueOf(iteration));
        this.fitnessPopulation = new SimpleStringProperty(String.valueOf(population.getGoodness()));
        this.bits = new SimpleStringProperty(genotype.toString());
    }
    public SimpleStringProperty fitnessIndividualProperty(){return fitnessIndividual;}
    public SimpleStringProperty ageProperty(){return age;}
    public SimpleStringProperty iterationProperty() {return iteration;}
    public StringProperty fitnessPopulationProperty() {return fitnessPopulation;}
    public SimpleStringProperty bitsProperty(){return bits;}
}
