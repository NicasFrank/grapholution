package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.OnePointCrossover;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.Recombinator;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class GeneticAlgorithmTest {

    /**
     * diese Testfunktion dient nur zur Veranschaulichung und zum einfachen Testen des genetischen Algorithmus
     */
    @Test
    void test() {
        Random rnd = new Random();

        FitnessFunction<Boolean> fitnessfunction = new OneMaxEvaluator();
        Mutator<Boolean> mutator = new SwitchOneBit();
        Recombinator<Boolean> recombinator = new OnePointCrossover<>();

        Genotype<Boolean> genotype = new Genotype<>(rand -> rnd.nextBoolean(), fitnessfunction, 5);

        Population<Boolean> population = new Population<>(rand -> rnd.nextBoolean(),50, 10, fitnessfunction);
        GeneticAlgorithm<Boolean> geneticAlgorithm = new GeneticAlgorithm<>( mutator, recombinator, 0.4, population);

        Genotype<Boolean> result = geneticAlgorithm.run();

        System.out.println(result.getValues());
    }
}