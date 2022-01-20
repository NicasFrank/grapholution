package de.htwk.leipzig.grapholution.javafxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model-Klasse um besten Genotypen, mit Property-Variablen
 */

public class BestGenotype {

    private final StringProperty fitness = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();

    /**
     * default Constructor
     * @param fitness
     * @param age
     */
    public BestGenotype(int fitness, int age) {

    }

    /**
     * getter für Alter
     * @return alter
     */
    public String getAge() {
        return age.get();
    }

    /**
     * getter mit Property
     * @return Alter
     */
    public StringProperty ageProperty() {
        return age;
    }

    /**
     * setter für Alter
     * @param age
     */
    public void setAge(String age) {
        this.age.set(age);
    }

    /**
     * getter für Fitness
     * @return Fitness
     */
    public String getFitness() {
        return fitness.get();
    }

    /**
     * getter mit Property
     * @return Fitness
     */
    public StringProperty fitnessProperty() {
        return fitness;
    }

    /**
     * setter für Fitness
     * @param fitness
     */
    public void setFitness(String fitness) {
        this.fitness.set(fitness);
    }
}
