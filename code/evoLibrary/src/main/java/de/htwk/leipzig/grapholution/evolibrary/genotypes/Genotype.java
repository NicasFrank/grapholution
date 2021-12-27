package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasse zur Darstellung eines einzelnen Genotypen
 * @param <T> Datentyp der Werte des Genotypen
 */
public class Genotype<T> {

    protected List<T> values;
    protected final FitnessFunction<T> fitnessFunction;
    protected int age = 0;
    protected int fitness;
    public final int MAX_FITNESS_VALUE;

    /**
     * Konstruktor zur Erstellung eines Genotypen mit vorgenerierten Werten als List
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param values Werte, die dem Genotyp zugewiesen werden
     */
    public Genotype(FitnessFunction<T> fitnessfunction, List<T> values){
        this.values = new ArrayList<>(values);
        this.fitnessFunction = fitnessfunction;
        this.fitness = fitnessFunction.evaluate(this);
        MAX_FITNESS_VALUE = fitnessFunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Getter fuer Werte des Genotyps
     * @return Liste mit Werten des Genotyps
     */
    public List<T> getValues() {
        return new ArrayList<>(values);
    }

    public int size(){
        return values.size();
    }

    public void set(int i, T element){
        values.set(i, element);
    }

    public T get(int i){
        return values.get(i);
    }

    /**
     * Konstruktor zur Erstellung eines Genotypen mit zufaellig generierten Werten als ArrayList einer bestimmten Länge
     * @param creator funktion zum erstellen Zufälliger Werte des Individuums
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param size Gewuenschte Laenge des Genotypen
     */
    public Genotype(Function<Random, T> creator, FitnessFunction<T> fitnessfunction, int size){
        values = (Stream.generate( () -> creator.apply(ThreadLocalRandom.current()))
                .limit(size)
                .collect(Collectors.toList()));
        this.fitnessFunction = fitnessfunction;
        this.fitness = fitnessfunction.evaluate(this);
        MAX_FITNESS_VALUE = fitnessfunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Getter für Fitnesswert des Genotypen
     * @return Fitnesswert des Genotypen
     */
    public int getFitness(){
        return fitness;
    }

    public FitnessFunction<T> getFitnessFunction(){
        return fitnessFunction;
    }

    /**
     * Funktion zur Erstellung einer Kopie eines Genotypen
     * @return Kopie des Genotypen
     */
    public Genotype<T> createCopy(){
        return new Genotype<>(fitnessFunction, new ArrayList<>(values));
    }

    /**
     * Funktion zur erneuten Evaluierung des Fitnesswerts des Genotypen
     */
    public void updateFitness(){
        this.fitness = fitnessFunction.evaluate(this);
    }

    /**
     * Funktion zur Erhöhung des Alters um 1
     */
    public void survive(){
        age++;
    }

    /**
     * Getter für Alter des Genotypen
     * @return Alter des Genotypen
     */
    public int getAge(){
        return age;
    }

    /**
     * Funktion zur Ausgabe der Werte des Genotypen auf der Konsole
     */
    public void print(){
        values.forEach(value -> System.out.print(value + " "));
        System.out.println("\n");
    }

    /**
     * Funktion zur Ausgabe des optimierungsziels der fitnessfunction
     * @return T Werte der Methode type()
     */
    public T getFitnessTarget(){
        return fitnessFunction.target();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fitnessFunction, age, fitness, MAX_FITNESS_VALUE);
    }
}
