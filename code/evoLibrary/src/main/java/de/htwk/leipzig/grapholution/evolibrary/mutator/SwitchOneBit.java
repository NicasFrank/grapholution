package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.Random;

public class SwitchOneBit implements Mutator<Boolean> {

    private final Random random = new Random();

    public SwitchOneBit(){

    }

    @Override
    public void mutate(Genotype<Boolean> genotype) {
        int index = random.nextInt(genotype.length());
        Boolean newBit = !genotype.getValues().get(index);
        genotype.getValues().set(index, newBit);
        genotype.updateFitness();
    }

}
