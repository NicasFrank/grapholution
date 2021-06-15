package de.htwk.leipzig.grapholution.evolibrary.genotype;

import java.util.ArrayList;

public class GenotypeBool implements Genotype<Boolean> {

    private final ArrayList<Boolean> values;
    private int age = 0;

    public GenotypeBool(ArrayList<Boolean> values){
        this.values = values;
    }


    public Boolean valueAt(int index){
        return values.get(index);
    }

    public ArrayList<Boolean> getValues(){
        return values;
    }

    @Override
    public int lenght() {
        return values.size();
    }

    public GenotypeBool createCopy(){
        ArrayList<Boolean> valueCopy = new ArrayList<>(values);
        return new GenotypeBool(valueCopy);
    }

    public void survive(){
        age++;
    }

    public int getAge(){
        return age;
    }

    public void print(){
        for (Boolean value : values) {
            System.out.print(value + " ");
        }
    }

}
