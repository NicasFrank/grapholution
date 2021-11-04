package de.htwk.leipzig.grapholution.evolibrary.selectors;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.*;

/**
 * Klasse zur Fitnessproportionalen Selektion von Individuen einer Population
 * @param <T> Datentyp der zu mutierenden Genotypen
 */
public class FitnessproportionalSelection<T> implements Selector<T>{

    private Population population;

    /**
     * Konstruktor der Selector-Klasse
     * @param population, Population aus der selektiert werden soll
     */
    public FitnessproportionalSelection(Population population){
        this.population = population;
    }

    /**
     * Selektion mehrerer Individuen mittels der Fitnessproportionalen Selektion
     * @param numberOfIndividuals Anzahl der auszuwählenden Individuen
     * @return Population aus selektierten Genotypen
     */
    public Population select(int numberOfIndividuals){
        Random rand = new Random();
        int sum = 0;
        Set<Genotype> selected = new HashSet<>();

        List<Integer> fitness = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            fitness.add(population.get(i).getFitness());
            sum += population.get(i).getFitness();
        }

        while(selected.size() < numberOfIndividuals) {
            int selection = rand.nextInt(sum);
            int accFitness = 0;
            for (int j = 0; j < population.size(); j++) {
                accFitness += population.get(j).getFitness();
                if (accFitness > selection) {
                    selected.add(population.get(j));
                }
            }
        }
        return new Population(selected);
    }
}
