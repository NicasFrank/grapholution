package de.htwk.leipzig.grapholution.evolibrary.genotype;

import java.util.ArrayList;
import java.util.Random;

public class GenotypeBoolean implements Genotype<Boolean> {

    private final ArrayList<Boolean> values;
    private final int length;
    private int age = 0;

    public GenotypeBoolean(ArrayList<Boolean> values){
        this.values = values;
        this.length = values.size();
    }

    public GenotypeBoolean(int size){
        this.length = size;
        Random random = new Random();
        ArrayList<Boolean> save = new ArrayList<>();
        for(int i = 0; i<this.length; i++){
            if(random.nextBoolean()){
                save.add(Boolean.TRUE);
            }
            else {
                save.add(Boolean.FALSE);
            }
        }
        this.values = save;
    }

    @Override
    public Boolean valueAt(int index){
        return values.get(index);
    }

    @Override
    public ArrayList<Boolean> getValues(){
        return values;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public GenotypeBoolean createCopy(){
        ArrayList<Boolean> valueCopy = new ArrayList<>(values);
        return new GenotypeBoolean(valueCopy);
    }

    @Override
    public Genotype<Boolean> generateGenotype() {
        Random random = new Random();
        ArrayList<Boolean> save = new ArrayList<>();
        for(int i = 0; i<this.length; i++){
            if(random.nextBoolean()){
                save.add(Boolean.TRUE);
            }
            else {
                save.add(Boolean.FALSE);
            }
        }
        return new GenotypeBoolean(save);
    }

    @Override
    public void survive(){
        age++;
    }

    @Override
    public int getAge(){
        return age;
    }

    @Override
    public void print(){
        for (Boolean value : values) {
            System.out.print(value + " ");
        }
    }

}
