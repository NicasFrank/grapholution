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
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import de.htwk.leipzig.grapholution.evolibrary.selectors.Selector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GeneticAlgorithmTest {
    Algorithm<Boolean> geneticAlgorithm;
    Population<Boolean> testPopulation;
    FitnessFunction<Boolean> evaluator;
    @Mock
    FitnessproportionalSelection<Boolean> selectorMock;

    @BeforeEach
    void setup() {
        evaluator = new OneMaxEvaluator();
        testPopulation = new Population<>(Random::nextBoolean, 10, 10, evaluator);
    }

    @Test
    void run_whenCalled_ReturnsBestPossibleIndividuum() {
        when(selectorMock.select(any())).thenCallRealMethod();

        geneticAlgorithm = new GeneticAlgorithm<>(
                new SwitchOneBit(),
                selectorMock,
                new OnePointCrossover<>(),
                0.5,
                testPopulation
        );

        var result = geneticAlgorithm.run();

        assertEquals(result.getFitness(), evaluator.getMaxFitnessValue(result));
    }

    @Test
    void run_LimitIsSet_OnlyRunSetAmountOfTimes() {
        when(selectorMock.select(any())).thenCallRealMethod();
        var limit = 2;

        geneticAlgorithm = new GeneticAlgorithm<>(
                new SwitchOneBit(),
                selectorMock,
                new OnePointCrossover<>(),
                0.5,
                testPopulation,
                2
        );

        geneticAlgorithm.run();

        verify(selectorMock, times(limit)).select(any());
    }
}