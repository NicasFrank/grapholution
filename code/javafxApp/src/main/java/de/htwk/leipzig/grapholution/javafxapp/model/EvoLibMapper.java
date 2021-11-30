package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.ArrayList;
import java.util.List;

public class EvoLibMapper {
    public List<BestGenotype> map(List<Genotype<Boolean>> results) {
        List<BestGenotype> listReturn = new ArrayList<>();
        for (Object g : results
        ) {
            Genotype<Boolean> h = (Genotype<Boolean>) g;
            listReturn.add(new BestGenotype(h.getFitness(), h.getAge()));
        }
        return listReturn;
    }

    public BestGenotype map(Genotype<Boolean> genotype) {
        return new BestGenotype(genotype.getFitness(), genotype.getAge());
    }
}
