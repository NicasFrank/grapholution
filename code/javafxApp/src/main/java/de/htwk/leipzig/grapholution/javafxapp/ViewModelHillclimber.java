package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;

public class ViewModelHillclimber{

    private BestGenotype bestGenotype;
    private Genotype<Boolean> genotype;
    private EvoLibMapper evoLibMapper;
    private Hillclimber hillclimber;

    /**
     * @param mutatorIsBinary ob die mutation binary ist wenn nicht switch one bit
     * @param mutationChance die wahrscheinlichkeit der binary mutation
     * @param limit die maximale anzahl an generationen
     */
    public ViewModelHillclimber(boolean mutatorIsBinary, double mutationChance, int limit, Genotype<Boolean> genotype){
        Mutator<Boolean> mutator;
        mutator = mutatorIsBinary ? new BinaryMutation((int) mutationChance) : new SwitchOneBit();
        if(limit < 0) {
            this.hillclimber = new Hillclimber<Boolean>(genotype, mutator, (int) limit);
        }
        else{
            this.hillclimber = new Hillclimber<Boolean>(genotype, mutator);
        }
        runAlgorithm(false);
    }

    /**
     * ruft die run methoden des Hillclimber algorithmus
     * @param untilDone ob der algorithmus bis zur maximalanzahl der generationen oder bestmoeglichen individuums durchlaufen soll
     * @return aktuell besten genotypen
     */
    public BestGenotype runAlgorithm(boolean untilDone){
        //Genotype currentBestGeno;
        if(!untilDone) {
            //currentBestGeno =
                    hillclimber.run();
        }
        else{

            //currentBestGeno =
                    hillclimber.run();
        }
        int fitness = hillclimber.run().getFitness();
        int age = hillclimber.run().getAge();;
        bestGenotype = new BestGenotype(fitness,age);

        //System.out.println(bestGenotype +" Iteration: " + hillclimber.getIterations());

        return bestGenotype;
    }
}
