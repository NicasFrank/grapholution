package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotype.GenotypeBool;

import java.util.Random;

public class SwitchOneBit implements Mutator<GenotypeBool> {

    public SwitchOneBit(){

    }

    @Override
    public void mutate(GenotypeBool genotype) {
        Random rand = new Random();
        int index = rand.nextInt(genotype.lenght());
        Boolean newBit = !genotype.getValues().get(index);
        genotype.getValues().set(index, newBit);
    }

}
