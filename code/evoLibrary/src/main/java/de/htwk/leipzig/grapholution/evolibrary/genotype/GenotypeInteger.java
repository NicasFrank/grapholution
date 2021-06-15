package de.htwk.leipzig.grapholution.evolibrary.genotype;

import de.htwk.leipzig.grapholution.evolibrary.fitnessfun.Fitnessfunction;

import java.util.ArrayList;

public class GenotypeInteger extends Genotype<Integer> {

    public GenotypeInteger(Fitnessfunction<Integer> fitnessfunction, ArrayList<Integer> values) {
        super(fitnessfunction, values);
    }

    public Genotype<Integer> generateGenotype() {
        return null;
    }

}
