package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    private List<Population<?>> history;
    private List<Genotype<?>> bestIndividuals;

    public Statistics(){
        history = new ArrayList<>();
        bestIndividuals = new ArrayList<>();
    }

    public void addToHistory(Population<?> population) {
        history.add(population);
        bestIndividuals.add(population.getBestIndividual());
    }

    public void addBestIndividual(Genotype<?> genotype){bestIndividuals.add(genotype);}

}
