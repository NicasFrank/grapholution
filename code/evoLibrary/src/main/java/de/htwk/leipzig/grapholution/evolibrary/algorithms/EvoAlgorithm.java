package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;

public interface EvoAlgorithm<T> {

    Genotype<T> run();

}
