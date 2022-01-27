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

    public FitnessFunction<T> getFitnessFunction(){
        return fitnessFunction;
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

    public abstract List<T> valuesToList();

    public abstract int size();

    public abstract void set(int i, T element);

    public abstract T get(int i);

    public abstract Genotype<T> createCopy();

    public abstract String toString();


}
