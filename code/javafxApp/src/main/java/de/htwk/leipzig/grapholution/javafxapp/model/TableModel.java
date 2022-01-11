package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import javafx.beans.property.SimpleStringProperty;

public class TableModel {
  private final SimpleStringProperty fitnessIndividual;
  private final SimpleStringProperty age;
  private final SimpleStringProperty iteration;
  //private final StringProperty fitnessPopulation;

  public TableModel(int iteration,Genotype<Boolean> genotype, Population<Boolean> population) {
    this.fitnessIndividual = new SimpleStringProperty(String.valueOf(genotype.getFitness()));
    this.age = new SimpleStringProperty(String.valueOf(genotype.getAge()));
    this.iteration = new SimpleStringProperty(String.valueOf(iteration));
    //this.fitnessPopulation = population.fitnessProperty();
  }
  public SimpleStringProperty fitnessIndividualProperty(){return fitnessIndividual;}
  public SimpleStringProperty ageProperty(){return age;}
  public SimpleStringProperty iterationProperty() {
    return iteration;
  }
  /*public StringProperty fitnessPopulationProperty() {
    return fitnessPopulation;
  }*/
}
