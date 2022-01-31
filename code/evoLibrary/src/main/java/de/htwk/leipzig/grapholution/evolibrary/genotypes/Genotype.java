package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;

import java.util.List;
/**
 * Klasse zur Darstellung eines einzelnen Genotypen
 * @param <T> Datentyp der Werte des Genotypen
 */
public abstract class Genotype<T> {
    protected final FitnessFunction<T> fitnessFunction;
    protected int age = 0;
    protected int fitness;
    protected int maxFitnessValue;


    protected Genotype(FitnessFunction<T> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    /**
     * Getter fuer maximalen Fitnesswert des Genotyps
     * @return Maximaler Fitnesswert des Genotyps
     */
    public int getMaxFitnessValue() {
        return maxFitnessValue;
    }

    /**
     * Getter für Fitnesswert des Genotypen
     * @return Fitnesswert des Genotypen
     */
    public int getFitness(){
        return fitness;
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
     * Funktion zur Ausgabe des optimierungsziels der fitnessfunction
     * @return T Werte der Methode type()
     */
    public T getFitnessTarget(){
        return fitnessFunction.target();
    }

    /**
     * Getter für Alter des Genotypen
     * @return Alter des Genotypen
     */
    public int getAge(){
        return age;
    }

    /**
     * Gibt Werte des Genotyps als Liste zurueck
     * @return Werte des Genotyps als Liste dargestellt
     */
    public abstract List<T> valuesToList();


    /**
     * Gibt Groesse des Genotyps zurueck
     * @return Groesse des Genotyps
     */
    public abstract int size();

    /**
     * Aendert Wert an bestimmter Stelle im Genotyp
     * @param i Stelle im Genotyp die abgeaendert werden soll
     * @param element Gewuenschter Wert
     */
    public abstract void set(int i, T element);

    /**
     * Gibt Wert an bestimmter Stelle im Genotyp zurueck
     * @param i Stelle im Genotyp deren Wert ermittelt werden soll
     * @return Wert an der Stelle
     */
    public abstract T get(int i);

    /**
     * Erzeugt eine Kopie des Genotyps
     * @return Kopie des Genotyps
     */
    public abstract Genotype<T> createCopy();

    /**
     * Wandelt den Genotyp in einen String um
     * @return Genotyp als String dargestellt
     */
    public abstract String toString();


}
