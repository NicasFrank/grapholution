package de.htwk.leipzig.grapholution.javafxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BestGenotype {

    private final StringProperty fitness = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();

    public BestGenotype(int fitness, int age) {

    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getFitness() {
        return fitness.get();
    }

    public StringProperty fitnessProperty() {
        return fitness;
    }

    public void setFitness(String fitness) {
        this.fitness.set(fitness);
    }
}
