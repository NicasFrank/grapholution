package de.htwk.leipzig.grapholution.evolibrary.genotypes;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;

import java.util.ArrayList;
import java.util.List;

public class Genotype<T> {

    protected final Fitnessfunction<T> fitnessfunction;
    protected List<T> values;
    protected final int length;
    protected int age = 0;
    protected int fitness;
    public final int MAX_FITNESS_VALUE;

    public Genotype(Fitnessfunction<T> fitnessfunction, ArrayList<T> values){
        this.fitnessfunction = fitnessfunction;
        this.values = values;
        this.length = values.size();
        this.fitness = fitnessfunction.evaluate(this);
        MAX_FITNESS_VALUE = fitnessfunction.getMaxFitnessValue(this);
    }

    public Genotype(Fitnessfunction<T> fitnessfunction, int size){
        this.fitnessfunction = fitnessfunction;
        this.fitness = fitnessfunction.evaluate(this);
        this.length = size;
        MAX_FITNESS_VALUE = fitnessfunction.getMaxFitnessValue(this);
    }

    public Fitnessfunction<T> getFitnessfunction() {return fitnessfunction;}

    public int getFitness(){
        return fitness;
    }

    public T valueAt(int index){
        return values.get(index);
    }

    public List<T> getValues(){
        return values;
    }

    public void setValues(ArrayList<T> newValues){this.values = newValues;}

    public int length() {
        return length;
    }

    public Genotype<T> createCopy(){
        ArrayList<T> valueCopy = new ArrayList<>(values);
        return new Genotype<>(fitnessfunction, valueCopy);
    }

    public void updateFitness(){
        this.fitness = fitnessfunction.evaluate(this);
    }

    public void survive(){
        age++;
    }

    public int getAge(){
        return age;
    }

    public void print(){
        for (T value : values) {
            System.out.print(value + " ");
        }
        System.out.println("\n");
    }
}
