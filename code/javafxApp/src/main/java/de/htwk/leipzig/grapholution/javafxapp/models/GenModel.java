package de.htwk.leipzig.grapholution.javafxapp.models;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse zur tabellarischen Darstellung des Verlaufs des genetischen Algorithmus
 */
public class GenModel {

    private final SimpleStringProperty fitnessIndividual;
    private final SimpleStringProperty age;
    private final SimpleStringProperty iteration;
    private final SimpleStringProperty fitnessPopulation;
    private final SimpleStringProperty bits;

    /**
     * Konstruktor aus einem Genotyp und einer Population
     * @param iteration Die Iteration des Algorithmus, an der der Genotyp entstanden ist
     * @param genotype Der Genotyp, aus dem das GenModel erstellt wird
     * @param population Die Population, aus der das GenModel erstellt wird
     */
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
