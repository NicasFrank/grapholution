package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.ListGenotype;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SwitchOneBitTests {
    Mutator<Boolean> switchOneBit;
    Genotype<Boolean> testGenotype;
    @Mock
    FitnessFunction<Boolean> fitnessMock;

    @BeforeEach
    void setup() {
        testGenotype = new ListGenotype<>(Random::nextBoolean, fitnessMock, 10);
    }

    @Test
    public void mutate_WhenCalled_FlipsOneBit() {
        var copy = testGenotype.createCopy();
        switchOneBit = new SwitchOneBit();

        testGenotype = switchOneBit.mutate(testGenotype);

        var trueCount = testGenotype.valuesToList().stream()
                .filter(aBoolean -> aBoolean)
                .count();

        var copyTrueCount = copy.valuesToList().stream()
                .filter(aBoolean -> aBoolean)
                .count();

        assertEquals(1, Math.abs(trueCount - copyTrueCount));
    }
}
