package de.htwk.leipzig.grapholution.evolibrary.genotype;

public interface Genotype<T> {

    int lenght();
    void survive();
    Genotype<T> createCopy();
    void print();
    int getAge();
}
