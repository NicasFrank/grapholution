package de.htwk.leipzig.grapholution.evolibrary.statistics;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Verwaltung der Populations-Historie und Besten-Individuums-Historie eines Algorithmus, sowie Auswertung dieser
 */
public class Statistics {

    private List<Population<?>> history = new ArrayList<>();
    private List<Genotype<?>> bestIndividuals = new ArrayList<>();

    /**
     * Funktion zum Hinzufuegen einer Population des Algorithmus zur Historie, sowie deren bestes Individuum zur
     * Besten-Individuums-Historie
     * @param population Population die hinzugefuegt werden soll
     */
    public void addToHistory(Population<?> population) {
        history.add(population);
        bestIndividuals.add(population.getBestIndividual());
    }

    /**
     * Funktion zum hinzufügen eines besten Individuums zur Besten-Individuums-Historie
     * @param genotype Individuum das hinzugefuegt werden soll
     */
    public void addBestIndividual(Genotype<?> genotype){bestIndividuals.add(genotype);}

    /**
     * Funktion zum erhalt des Proznetanteils der Bits der Stellen des Genotypes die dem Fitnessziel entsprechen einer Population
     * @return Array mit jeweiligen prozentvorkommen des Fitnessziels an der entsprechenden Stelle
     */
    public float[] getColorBitString(Population<?> population){
        float[] array = new float[population.get(0).size()];
        for(int j = 0; j <= array.length; j++){
            array[j] = 0f;
        }
        for (Genotype<?> g: population) {
            for(int i = 0; i < g.size(); i++){
                if(g.getValues().get(i) == g.getFitnessTarget()){
                    array[i] += 1f;
                }
            }
        }
        for(int j = 0; j <= array.length; j++){
            array[j] = array[j]/population.size();
        }
        return array;
    }
}
