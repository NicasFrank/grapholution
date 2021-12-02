package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    private List<Population<?>> history;

    public Statistics(){
        history = new ArrayList<>();
    }

    public void addToHistory(Population<?> population) {
        history.add(population);
    }

}
