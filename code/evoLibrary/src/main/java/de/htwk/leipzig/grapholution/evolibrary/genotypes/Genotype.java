package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Klasse zur Darstellung eines einzelnen Genotypen
 * @param <T> Datentyp der Werte des Genotypen
 */
public class Genotype<T> {

    protected final Fitnessfunction<T> fitnessfunction;
    protected List<T> values;
    protected final int length;
    protected int age = 0;
    protected int fitness;
    public final int MAX_FITNESS_VALUE;

    /**
     * Konstruktor zur Erstellung eines Genotypen mit vorgenerierten Werten als List
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param values Werte, die dem Genotyp zugewiesen werden
     */
    public Genotype(Fitnessfunction<T> fitnessfunction, List<T> values){
        this.fitnessfunction = fitnessfunction;
        this.values = values;
        this.length = values.size();
        this.fitness = fitnessfunction.evaluate(this);
        MAX_FITNESS_VALUE = fitnessfunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Konstruktor zur Erstellung eines Genotypen mit zufaellig generierten Werten als ArrayList einer bestimmten Länge
     * @param creator funktion zum erstellen Zufälliger Werte des Individuums
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param size Gewuenschte Laenge des Genotypen
     */
    public Genotype(Function<Random, T> creator, Fitnessfunction<T> fitnessfunction, int size){
        this.values = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            this.values.add(creator.apply(rand));
        }

        this.fitnessfunction = fitnessfunction;
        this.fitness = fitnessfunction.evaluate(this);
        this.length = size;
        MAX_FITNESS_VALUE = fitnessfunction.getMaxFitnessValue(this);
        updateFitness();
    }

    /**
     * Getter fuer Fitnesswert des Genotypen
     * @return Fitnesswert des Genotypen
     */
    public int getFitness(){
        return fitness;
    }

    /**
     * Getter fuer Wert des Genotypen an bestimmter Stelle der ArrayList
     * @param index Stelle der ArrayList die ausgelesen werden soll
     * @return Wert des Genotypen an bestimmter Stelle der ArrayList
     */
    public T valueAt(int index){
        return values.get(index);
    }

    /**
     * Getter fuer alle Werte des Genotypen
     * @return List mit allen Werten des Genotypen
     */
    public List<T> getValues(){
        return values;
    }

    /**
     * Setter fuer die List mit den Werten des Genotypen
     * @param newValues Werte, die dem Genotypen zugewiesen werden sollen
     */
    public void setValues(List<T> newValues){
        this.values = newValues;
    }

    /**
     * Getter fuer die Laenge des Genotypen (Anzahl der Werte)
     * @return Laenge des Genotypen
     */
    public int length() {
        return length;
    }

    /**
     * Funktion zur Erstellung einer Kopie eines Genotypen
     * @return Kopie des Genotypen
     */
    public Genotype<T> createCopy(){
        ArrayList<T> valueCopy = new ArrayList<>(values);
        return new Genotype<>(fitnessfunction, valueCopy);
    }

    /**
     * Funktion zur erneuten Evaluierung des Fitnesswerts des Genotypen
     */
    public void updateFitness(){
        this.fitness = fitnessfunction.evaluate(this);
    }

    /**
     * Funktion zur Erhoehung des Alters um 1
     */
    public void survive(){
        age++;
    }

    /**
     * Getter fuer Alter des Genotypen
     * @return Alter des Genotypen
     */
    public int getAge(){
        return age;
    }

    /**
     * Funktion zur Ausgabe der Werte des Genotypen auf der Konsole
     */
    public void print(){
        for (T value : values) {
            System.out.print(value + " ");
        }
        System.out.println("\n");
    }
}
