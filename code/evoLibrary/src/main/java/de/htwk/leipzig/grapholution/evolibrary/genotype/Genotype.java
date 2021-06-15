package de.htwk.leipzig.grapholution.evolibrary.genotype;

import java.util.ArrayList;

public class Genotype<T> {

    private final ArrayList<T> values;
    private int age = 0;

    public Genotype(ArrayList<T> values){
        this.values = values;
    }

    public int length(){
        return values.size();
    }

    public T valueAt(int index){
        return values.get(index);
    }

    public ArrayList<T> getValues(){
        return values;
    }

    public Genotype<T> createCopy(){
        ArrayList<T> valueCopy = new ArrayList<>(values);
        return new Genotype<>(valueCopy);
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
    }

}
