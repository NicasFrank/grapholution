package de.htwk.leipzig.grapholution.evolibrary.models;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgorithmConfigOptionsTests {

    @Test
    public void serializeDeserialize_WhenCalled_ConfigObjectStillHasTheSameContent() {
        var file = new File("temp.temp");
        var options = new AlgorithmConfigOptions()
                .add(BoolConfig.FitnessIsOneMax, true)
                .add(IntConfig.GenotypeSize, 1)
                .add(BoolConfig.FitnessIsOneMax, true)
                .add(DoubleConfig.RecombinationChance, 1)
                .add(BoolConfig.MutationIsBinary, true);

        try {
            options.serialize(file);
            var deserializedOptions = new AlgorithmConfigOptions().deserialize(file);

            assertTrue(deserializedOptions.getBool(BoolConfig.FitnessIsOneMax));
            assertEquals(1, deserializedOptions.getInt(IntConfig.GenotypeSize));
            assertTrue(deserializedOptions.getBool(BoolConfig.FitnessIsOneMax));
            assertEquals(1, deserializedOptions.getDouble(DoubleConfig.RecombinationChance));
            assertTrue(deserializedOptions.getBool(BoolConfig.FitnessIsOneMax));
        } catch (Exception e) {
            assert false;
        } finally {
            if (!file.delete()) {
                System.out.println("Temporary test file could not be deleted!");
            }
        }
    }

    @Test
    public void getOrElse_OptionDoesNotExist_ReturnFallback() {
        var options = new AlgorithmConfigOptions();

        assertEquals(-1, options.getOrElse(IntConfig.Limit, -1));
        assertEquals(-1.11, options.getOrElse(DoubleConfig.RecombinationChance, -1.11));
        assertTrue(options.getOrElse(BoolConfig.IsStepByStep, true));
    }
}
