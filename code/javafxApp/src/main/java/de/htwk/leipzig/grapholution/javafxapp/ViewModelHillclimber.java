package de.htwk.leipzig.grapholution.javafxapp;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Hillclimber.Hillclimber;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.BinaryMutation;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.javafxapp.model.EvoLibMapper;
import de.htwk.leipzig.grapholution.javafxapp.model.BestGenotype;

public class ViewModelHillclimber{

    private BestGenotype bestGenotype;
    private Genotype genotype;
    private EvoLibMapper evoLibMapper;
    private Hillclimber hillclimber;

    /**
     * @param mutatorIsBinary ob die mutation binary ist wenn nicht switch one bit
     * @param mutationChance die wahrscheinlichkeit der binary mutation
     * @param fitnessIsOneMax ob fitnessfunction one max ist sonst zero max
     * @param limit die maximale anzahl an generationen
     */
    public ViewModelHillclimber(boolean mutatorIsBinary, boolean fitnessIsOneMax, double mutationChance, int limit){
        Mutator mutator;
        FitnessFunction fitnessFunction;
        mutator = mutatorIsBinary ? new BinaryMutation((int) mutationChance) : new SwitchOneBit();
        fitnessFunction = fitnessIsOneMax ? new OneMaxEvaluator() : new OneMaxEvaluator();
        if(limit < 0) {
            hillclimber = new Hillclimber<Genotype>(genotype,mutator, (int) limit);
        }
        runAlgorithm(false);
    }

    /**
     * ruft die run methoden des Hillclimber algorithmus
     * @param untilDone ob der algorithmus bis zur maximalanzahl der generationen oder bestmoeglichen individuums durchlaufen soll
     * @return aktuell besten genotypen
     */
    public BestGenotype runAlgorithm(boolean untilDone){
        Genotype currentBestGeno = null;
        if(!untilDone) {
            currentBestGeno = hillclimber.run();
        }
        int fitness = currentBestGeno.getFitness();
        int age = currentBestGeno.getAge();
        bestGenotype = new BestGenotype(fitness,age);

        System.out.println(bestGenotype +" Iteration: " + hillclimber.getIterations());

        return bestGenotype;
    }
}
