package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfun;
import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

public class Algorithm<T> {

    protected Genotype<T> genotype;
    protected Fitnessfun<Genotype<T>> fitnessfun;
    protected Mutator<Genotype<T>> mutator;

    public Algorithm(Genotype<T> genotype, Fitnessfun<Genotype<T>> fitnessfun, Mutator<Genotype<T>> mutator){
        this.genotype = genotype;
        this.fitnessfun = fitnessfun;
        this.mutator = mutator;
    }

}
