package de.htwk.leipzig.grapholution.javafxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HistoryResults {
    private final SimpleStringProperty duration;
    private final SimpleStringProperty population;
    private final SimpleStringProperty fitness;

    /**
     * Constructor with some initial data.
     *
     * @param duration
     * @param population
     * @param fitness
     */

    public HistoryResults(String duration, String population, String fitness) {
        this.duration = new SimpleStringProperty(duration);
        this.population = new SimpleStringProperty(population);
        this.fitness = new SimpleStringProperty(fitness);
    }


    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public String getPopulation() {
        return population.get();
    }

    public void setPopulation(String population) {
        this.population.set(population);
    }

    public StringProperty populationProperty() {
        return population;
    }

    public String getFitness() {
        return fitness.get();
    }

    public void setFitness(String fitness) {
        this.fitness.set(fitness);
    }

    public StringProperty fitnessProperty() {
        return fitness;
    }
}
