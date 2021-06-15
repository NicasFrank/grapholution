package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;

import java.util.Random;

public class SwitchOneBit implements Mutator<Boolean> {

    public SwitchOneBit(){

    }

    @Override
    public void mutate(Genotype<Boolean> genotype) {
        Random rand = new Random();
        int index = rand.nextInt(genotype.length());
        Boolean newBit = !genotype.getValues().get(index);
        genotype.getValues().set(index, newBit);
    }

}
