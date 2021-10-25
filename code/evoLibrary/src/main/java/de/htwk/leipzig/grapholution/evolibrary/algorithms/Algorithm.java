package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

public abstract class Algorithm<T> {

    protected Genotype<T> genotype;
    protected int limit = -1;
    protected int iterations = 0;

    public Algorithm(Genotype<T> genotype){
        this.genotype = genotype;
    }

    public int getIterations() {
        return iterations;
    }

    public Algorithm(Genotype<T> genotype, Mutator<T> mutator, int limit) {
        this(genotype);
        this.limit = limit;
    }

    public Genotype<T> run(){

        return null;

    }

}
