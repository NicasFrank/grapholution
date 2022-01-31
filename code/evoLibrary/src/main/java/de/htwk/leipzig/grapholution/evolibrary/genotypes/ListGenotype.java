package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasse zur Darstellung eines einzelnen Genotypen mit einer Liste zur Speicherung der Werte
 * @param <T> Datentyp der Werte des Genotypen
 */
public class ListGenotype<T> extends Genotype<T> {
    protected List<T> values;

    /**
     * Konstruktor zur Erstellung eines Genotypen mit Werten aus der übergebenen Liste
     * @param fitnessFunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param values Werte, die dem Genotyp zugewiesen werden
     */
    public ListGenotype(FitnessFunction<T> fitnessFunction, List<T> values){
        super(fitnessFunction);
        this.values = new ArrayList<>(values);
        maxFitnessValue = this.fitnessFunction.getMaxFitnessValue(this);
        this.fitness = this.fitnessFunction.evaluate(this);
        updateFitness();
    }

    /**
     * Konstruktor zur Erstellung eines Genotypen mit zufällig generierten Werten als ArrayList einer bestimmten Länge
     * @param creator Funktion zum Erstellen zufälliger Werte des Individuums
     * @param fitnessFunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param size Gewünschte Länge des Genotypen
     */
    public ListGenotype(Function<Random, T> creator, FitnessFunction<T> fitnessFunction, int size){
        super(fitnessFunction);
        values = (Stream.generate( () -> creator.apply(ThreadLocalRandom.current()))
                .limit(size)
                .collect(Collectors.toList()));
        this.fitness = fitnessFunction.evaluate(this);
        maxFitnessValue = fitnessFunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Getter für Werte des Genotyps
     * @return Liste mit Werten des Genotyps
     */
    @Override
    public List<T> valuesToList() {
        return new ArrayList<>(values);
    }

    @Override
    public int size(){
        return values.size();
    }

    @Override
    public void set(int i, T element){
        values.set(i, element);
        updateFitness();
    }

    @Override
    public T get(int i){
        return values.get(i);
    }

    @Override
    public Genotype<T> createCopy(){
        return new ListGenotype<>(fitnessFunction, new ArrayList<>(values));
    }

    @Override
    public String toString() {
        return values.stream()
                .collect(
                        () -> new StringBuilder(values.size()),
                        StringBuilder::append,
                        StringBuilder::append
                )
                .toString();
    }
}
