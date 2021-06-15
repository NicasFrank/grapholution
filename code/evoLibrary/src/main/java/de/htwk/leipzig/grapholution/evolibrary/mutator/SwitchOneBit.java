package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBoolean;

import java.util.Random;

public class SwitchOneBit implements Mutator<GenotypeBoolean> {

    public SwitchOneBit(){

    }

    @Override
    public void mutate(GenotypeBoolean genotype) {
        Random rand = new Random();
        int index = rand.nextInt(genotype.length());
        Boolean newBit = !genotype.getValues().get(index);
        genotype.getValues().set(index, newBit);
    }

}
