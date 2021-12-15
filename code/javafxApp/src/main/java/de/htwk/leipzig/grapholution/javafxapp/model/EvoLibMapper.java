package de.htwk.leipzig.grapholution.javafxapp.model;

import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Population;
import de.htwk.leipzig.grapholution.evolibrary.statistics.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse für das Mapping der Lib Daten zu JavaFX
 */
public class EvoLibMapper {
    /**
     * Methode für das mappen einer Liste von Genotypen
     * @param results
     * @return
     */
    public List<BestGenotype> map(List<Genotype<Boolean>> results) {
        List<BestGenotype> listReturn = new ArrayList<>();
        for (Object g : results
        ) {
            Genotype<Boolean> h = (Genotype<Boolean>) g;
            listReturn.add(new BestGenotype(h.getFitness(), h.getAge()));
        }
        return listReturn;
    }

    /**
     * Getter für Besten Genotypen
     * @param genotype
     * @return bester Gentoyp
     */
    public BestGenotype map(Genotype<Boolean> genotype) {
        return new BestGenotype(genotype.getFitness(), genotype.getAge());
    }

    /**
     * Methode um Liste von HillModel zu erzeugen und befüllen
     * @param statistics
     * @return
     */
    public List<HillModel> mapStatModel(Statistics statistics) {
        List<HillModel> listReturn = new ArrayList<>();

        for (Object g : statistics.getBestIndividuals()) {
            Genotype<Boolean> h = (Genotype<Boolean>) g;
            listReturn.add(new HillModel(h.getFitness()));
        }
        return listReturn;
    }

    /**
     * Methode um Liste von GenModel zurückzugeben, welche eine Liste an HillModels enthält
     * @param statistics
     * @return
     */
    public List<GenModel> map(Statistics statistics) {
        List<GenModel> listReturn = new ArrayList<>();

        for (Object populationObject : statistics.getHistory()) {
            Population<Boolean> population = (Population<Boolean>) populationObject;
            List<HillModel> statListReturn = new ArrayList<>();
            for (Object genotypeObject: population){
                Genotype<Boolean> genotype = (Genotype<Boolean>) genotypeObject;
                statListReturn.add(new HillModel(genotype.getFitness()));
            }
            listReturn.add(new GenModel(statListReturn));
        }
        return listReturn;
    }
}
