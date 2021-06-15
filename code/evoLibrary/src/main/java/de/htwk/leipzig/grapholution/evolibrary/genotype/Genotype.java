package de.htwk.leipzig.grapholution.evolibrary.genotype;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;

import java.util.ArrayList;

public class Genotype<T> {

    protected Fitnessfunction<T> fitnessfunction;
    protected ArrayList<T> values;
    protected int length;
    protected int age = 0;
    protected int fitness;

    public Genotype(Fitnessfunction<T> fitnessfunction, ArrayList<T> values){
        this.fitnessfunction = fitnessfunction;
        this.values = values;
        this.length = values.size();
        this.fitness = fitnessfunction.evaluate(this);
    }

    public Genotype(Fitnessfunction<T> fitnessfunction, int size){
        this.fitnessfunction = fitnessfunction;
        this.fitness = fitnessfunction.evaluate(this);
        this.length = size;
    }

    public int getFitness(){
        return fitness;
    }

    public T valueAt(int index){
        return values.get(index);
    }

    public ArrayList<T> getValues(){
        return values;
    }

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
