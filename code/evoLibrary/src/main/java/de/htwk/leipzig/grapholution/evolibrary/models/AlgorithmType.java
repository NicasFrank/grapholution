package de.htwk.leipzig.grapholution.evolibrary.models;

public enum AlgorithmType {
    Hillclimber("Hillclimber"),
    GeneticAlgorithm("Genetischer Algorithmus");

    public final String name;

    AlgorithmType(String name) {
        this.name = name;
    }
}
