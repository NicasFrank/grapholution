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

    /**
     * Konstruktor
     * @param fitnessFunction Die Fitnessfunktion zur Berechnung des Fitnesswertes des Genotypen
     */
    protected Genotype(FitnessFunction<T> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    /**
     * Gibt den maximalen Fitnesswert zurück, den dieser Genotyp erreichen kann
     * @return Den maximal erreichbaren Fitnesswert
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
     * Funktion zur Ausgabe des Optimierungsziels der Fitnessfunktion
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
     * Gibt die Werte des Genotypen als Liste zurück
     * @return Die Werte des Genotypen als Liste
     */
    public abstract List<T> valuesToList();

    /**
     * Gibt die Anzahl der Werte des Genotypen zurück
     * @return die Anzahl der Werte des Genotypen
     */
    public abstract int size();

    /**
     * Setzt den übergebenen Wert an der Stelle i
     * @param i die Stelle, an der der Wert gesetzt werden soll
     * @param element der zu setzende Wert
     */
    public abstract void set(int i, T element);

    /**
     * Gibt den aktuellen Wert des Genotypen an der Stelle i zurück
     * @param i Die Stelle, dessen Wert zurückgegeben werden soll
     * @return Den Wert an der Stelle i
     */
    public abstract T get(int i);

    /**
     * Gibt eine identische Kopie des Genotypen zurück
     * @return eine identische Kopie des Genotypen
     */
    public abstract Genotype<T> createCopy();

    /**
     * Gibt die Werte des Genotype als String zurück
     * @return die Werte des Genotypen als String
     */
    public abstract String toString();
}
