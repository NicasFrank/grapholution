package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;
import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

public class Algorithm<T> {

    protected Genotype<T> genotype;
    protected Mutator<T> mutator;

    public Algorithm(Genotype<T> genotype, Mutator<T> mutator){
        this.genotype = genotype;
        this.mutator = mutator;
    }

}
