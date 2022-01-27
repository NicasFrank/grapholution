package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StatisticsTests {
    @Mock
    FitnessFunction<Boolean> fitnessMock;
    Statistics<Boolean> statistics;

    @BeforeEach
    public void setup() {
        statistics = new Statistics<>();
    }

    @Test
    public void addHistory_WhenCalled_AddsBestIndividuumOfPopulationToBestIndividuals() {
        var population = new Population<>(() -> new BitSetGenotype(Random::nextBoolean, fitnessMock, 10), 10);
        statistics.addToHistory(population);

        assertEquals(population.getBestIndividual(), statistics.getBestIndividuals().get(0));
    }

    @Test
    public void createCopy_WhenCalled_CreatesNewStatisticsObjectWithSameContent() {
        var population1 = new Population<>(() -> new BitSetGenotype(Random::nextBoolean, fitnessMock, 10), 10);
        var population2 = new Population<>(() -> new BitSetGenotype(Random::nextBoolean, fitnessMock, 10), 10);
        statistics.addToHistory(population1);
        statistics.addToHistory(population2);

        var copy = statistics.createCopy();

        assertEquals(statistics.getHistory().get(0), copy.getHistory().get(0));
        assertEquals(statistics.getHistory().get(1), copy.getHistory().get(1));
        assertEquals(statistics.getBestIndividuals().get(0), copy.getBestIndividuals().get(0));
        assertEquals(statistics.getBestIndividuals().get(1), copy.getBestIndividuals().get(1));
    }
}
