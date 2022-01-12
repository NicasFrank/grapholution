package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfunction.FitnessFunction;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.BitSetGenotype;
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
    BitSetGenotype testGenotype;
    @Mock
    FitnessFunction<Boolean> fitnessMock;

    @BeforeEach
    void setup() {
        testGenotype = new BitSetGenotype(Random::nextBoolean, fitnessMock, 10);
    }

    @Test
    public void mutate_WhenCalled_FlipsOneBit() {
        var copy = (BitSetGenotype) testGenotype.createCopy();
        switchOneBit = new SwitchOneBit();

        testGenotype = (BitSetGenotype) switchOneBit.mutate(testGenotype);

        var oneCount = testGenotype.oneCount();

        var copyOneCount = copy.oneCount();

        assertEquals(1, Math.abs(oneCount - copyOneCount));
    }
}
