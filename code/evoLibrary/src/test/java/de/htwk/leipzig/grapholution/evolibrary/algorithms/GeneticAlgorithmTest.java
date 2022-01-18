package de.htwk.leipzig.grapholution.evolibrary.algorithms;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.GeneticAlgorithm.GeneticAlgorithm;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.OneMaxEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.models.AlgorithmConfigOptions;
import de.htwk.leipzig.grapholution.evolibrary.models.DoubleConfig;
import de.htwk.leipzig.grapholution.evolibrary.models.IntConfig;
import de.htwk.leipzig.grapholution.evolibrary.mutator.SwitchOneBit;
import de.htwk.leipzig.grapholution.evolibrary.recombinator.OnePointCrossover;
import de.htwk.leipzig.grapholution.evolibrary.selectors.FitnessproportionalSelection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GeneticAlgorithmTest {
    GeneticAlgorithm<Boolean> geneticAlgorithm;
    Population<Boolean> testPopulation;
    FitnessFunction<Boolean> evaluator;
    @Mock
    FitnessproportionalSelection<Boolean> selectorMock;

    @BeforeEach
    void setup() {
        evaluator = new OneMaxEvaluator();
        testPopulation = new Population<>(() -> new BitSetGenotype(r -> false, evaluator, 10), 10);
    }

    @Test
    void run_whenCalled_ReturnsBestPossibleIndividuum() {
        when(selectorMock.select(any())).thenCallRealMethod();

        geneticAlgorithm = new GeneticAlgorithm<>(
                new SwitchOneBit(),
                selectorMock,
                new OnePointCrossover<>(),
                testPopulation,
                new AlgorithmConfigOptions()
                        .add(DoubleConfig.RecombinationChance, 0.5)
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
                testPopulation,
                new AlgorithmConfigOptions()
                        .add(DoubleConfig.RecombinationChance, 0.5)
                        .add(IntConfig.Limit, limit)
        );

        geneticAlgorithm.run();

        verify(selectorMock, times(limit)).select(any());
    }

    @Test
    void oneStep_WhenCalled_ExecutesOneIterationOfTheAlgorithm() {
        when(selectorMock.select(any())).thenCallRealMethod();

        geneticAlgorithm = new GeneticAlgorithm<>(
                new SwitchOneBit(),
                selectorMock,
                new OnePointCrossover<>(),
                testPopulation,
                new AlgorithmConfigOptions()
                        .add(DoubleConfig.RecombinationChance, 0.5)
        );

        geneticAlgorithm.oneStep();

        verify(selectorMock, times(1)).select(any());
    }

    @Test
    void oneStep_AlgorithmHasRunToCompletion_DoesNotExecuteIteration() {
        var limit = 0;

        geneticAlgorithm = new GeneticAlgorithm<>(
                new SwitchOneBit(),
                selectorMock,
                new OnePointCrossover<>(),
                testPopulation,
                new AlgorithmConfigOptions()
                        .add(DoubleConfig.RecombinationChance, 0.5)
                        .add(IntConfig.Limit, limit)
        );

        geneticAlgorithm.oneStep();

        verify(selectorMock, never()).select(any());
    }
}