package de.htwk.leipzig.grapholution.evolibrary.mutator;

import de.htwk.leipzig.grapholution.evolibrary.genotype.Genotype;

import java.util.Random;

public class BinaryMutation implements Mutator<Boolean> {

    private final int probability;

    public BinaryMutation(int probability){
        this.probability = probability;
    }


    @Override
    public void mutate(Genotype<Boolean> genotype) {
        Random random = new Random();
        for(int i = 0; i < genotype.length(); i++){
            if(random.nextInt(101)<probability){
                genotype.getValues().set(i, !genotype.valueAt(i));
            }
        }
        genotype.updateFitness();
    }
}
