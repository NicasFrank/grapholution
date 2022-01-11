package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;

import java.util.ArrayList;
import java.util.List;

public class EvoLibMapper {

    public List<BestGenotype> map(List<Genotype<Boolean>> results) {
        List<BestGenotype> listReturn = new ArrayList<>();
        for (Genotype<Boolean> g : results) {
            listReturn.add(new BestGenotype(g.getFitness(), g.getAge()));
        }
        return listReturn;
    }

    public BestGenotype map(Genotype<Boolean> genotype) {
        return new BestGenotype(genotype.getFitness(), genotype.getAge());
    }
}
