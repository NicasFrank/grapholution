package de.htwk.leipzig.grapholution.evolibrary.genotype;

import java.util.ArrayList;

public class GenotypeInteger implements Genotype<Integer> {

    private final ArrayList<Integer> values;
    private final int length;
    private int age = 0;

    public GenotypeInteger(ArrayList<Integer> values) {
        this.values = values;
        this.length = values.size();
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public void survive(){
        age++;
    }

    @Override
    public GenotypeInteger createCopy(){
        ArrayList<Integer> valueCopy = new ArrayList<>(values);
        return new GenotypeInteger(valueCopy);
    }

    @Override
    public Genotype<Integer> generateGenotype() {
        return null;
    }

    @Override
    public ArrayList<Integer> getValues(){
        return values;
    }

    @Override
    public Integer valueAt(int index){
        return values.get(index);
    }

    @Override
    public void print(){
        for (Integer value : values) {
            System.out.print(value + " ");
        }
    }

    @Override
    public int getAge(){
        return age;
    }
}
