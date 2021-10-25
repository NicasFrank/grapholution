package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;

import java.util.ArrayList;
import java.util.List;

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
     * Konstruktor zur Erstellung eines Genotypen mit vorgenerierten Werten als ArrayList
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param values Werte, die dem Genotyp zugewiesen werden
     */
    public Genotype(Fitnessfunction<T> fitnessfunction, ArrayList<T> values){
        this.fitnessfunction = fitnessfunction;
        this.values = values;
        this.length = values.size();
        this.fitness = fitnessfunction.evaluate(this);
        MAX_FITNESS_VALUE = fitnessfunction.getMaxFitnessValue(this);
    }

    /**
     * Konstruktor zur Erstellung eines Genotypen mit zufaellig generierten Werten einer bestimmten Länge
     * @param fitnessfunction Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     * @param size Gewuenschte Laenge des Genotypen
     */
    public Genotype(Fitnessfunction<T> fitnessfunction, int size){
        this.fitnessfunction = fitnessfunction;
        this.fitness = fitnessfunction.evaluate(this);
        this.length = size;
        MAX_FITNESS_VALUE = fitnessfunction.getMaxFitnessValue(this);
    }

    /**
     * Getter fuer Fitnessfunktion
     * @return Fitnessfunktion, die zur Evaluierung des Genotyps benutzt wird
     */
    public Fitnessfunction<T> getFitnessfunction() {return fitnessfunction;}

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
     * @return ArrayList mit allen Werten des Genotypen
     */
    public List<T> getValues(){
        return values;
    }

    /**
     * Setter fuer die ArrayList mit den Werten des Genotypen
     * @param newValues Werte, die dem Genotypen zugewiesen werden sollen
     */
    public void setValues(ArrayList<T> newValues){
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
