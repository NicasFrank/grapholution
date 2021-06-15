package de.htwk.leipzig.grapholution.evolibrary.evoInterface;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.EvoAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfun;
import de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

import java.util.ArrayList;

public class EvoAlg {

    private Genotype<?> gen;
    private Fitnessfun<?> fit;
    private Mutator<?> mut;
    private EvoAlgorithm alg;

    public EvoAlg(){
    }


}
