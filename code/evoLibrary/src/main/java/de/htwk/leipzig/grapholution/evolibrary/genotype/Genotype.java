package de.htwk.leipzig.grapholution.evolibrary.genotype;

import java.util.ArrayList;

public class Genotype<T> {

    protected ArrayList<T> values;
    protected int length;
    protected int age = 0;

    public Genotype(ArrayList<T> values){
        this.values = values;
        this.length = values.size();
    }

    public Genotype(int size){
        this.length = size;
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
        System.out.println("\n");
    }


}
