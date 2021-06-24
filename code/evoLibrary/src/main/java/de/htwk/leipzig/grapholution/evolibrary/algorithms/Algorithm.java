package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

public abstract class Algorithm<T> {

    protected Genotype<T> genotype;
    protected Mutator<T> mutator;
    protected int limit = -1;
    protected int iterations = 0;

    public Algorithm(Genotype<T> genotype, Mutator<T> mutator){
        this.genotype = genotype;
        this.mutator = mutator;
    }

    public int getIterations() {
        return iterations;
    }

    public Algorithm(Genotype<T> genotype, Mutator<T> mutator, int limit) {
        this(genotype, mutator);
        this.limit = limit;
    }

    public Genotype<T> run(){

        return null;

    }

}
