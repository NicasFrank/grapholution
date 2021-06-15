package de.htwk.leipzig.grapholution.evolibrary.genotype;

import java.util.ArrayList;

public interface Genotype<T> {

    int length();
    void survive();
    Genotype<T> createCopy();
    Genotype<T> generateGenotype();
    ArrayList<T> getValues();
    T valueAt(int index);
    void print();
    int getAge();
}
