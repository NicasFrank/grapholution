package de.htwk.leipzig.grapholution.evolibrary.algorithms.hillclimber;

import de.htwk.leipzig.grapholution.evolibrary.algorithms.Algorithm;
import de.htwk.leipzig.grapholution.evolibrary.genotypes.Genotype;
import de.htwk.leipzig.grapholution.evolibrary.mutator.Mutator;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Darstellung eines einfachen Hillclimbers mit einstellbarer Mutation und Genotyp
 * @param <T> Datentyp des Genotypen, auf dem der Hillclimber arbeitet
 */
public class Hillclimber<T> extends Algorithm<T> {

    private Genotype<T> currentBest; //Alle Genotypen, welche Verbesserungen brachten
    private final Mutator<T> mutator;

    /**
     * Konstruktor zur Erstellung eines einfachen Hillclimbers mit einstellbarer Mutation und Genotyp
     * @param genotype Genotyp auf dem der Algorithmus arbeiten soll
     * @param mutator Mutation, die zur Veraenderung des Genotypen benutzt werden soll
     */
    public Hillclimber(Genotype<T> genotype, Mutator<T> mutator) {
        super(genotype);
        this.mutator = mutator;
        currentBest = genotype;
    }

    /**
     * Konstruktor zur Erstellung eines einfachen Hillclimbers mit einstellbarer Mutation und Genotyp, sowie Limit der Durchlaeufe
     * @param genotype Genotyp auf dem der Algorithmus arbeiten soll
     * @param mutator Mutation, die zur Veraenderung des Genotypen benutzt werden soll
     * @param limit Maximale Anzahl an Durchlaeufen, die der Hillclimber durcharbeiten soll
     */
    public Hillclimber(Genotype<T> genotype, Mutator<T> mutator, int limit) {
        super(genotype, limit);
        this.mutator = mutator;
        currentBest = genotype;
        statistics.addBestIndividual(genotype);
    }

    /**
     * Funktion zum Ausfuehren des Hillclimbers
     * @return Bester erreichter Genotyp
     */
    public Genotype<T> run() {
            while (currentBest.getFitness() < currentBest.MAX_FITNESS_VALUE && //Ueberprufung ob Limit oder bestmoeglicher Genotyp bereits erreicht
                (limit < 0 || iterations < limit)){
            Genotype<T> mutation = mutator.mutate(currentBest); //Kopie des letzten Genotypen zur Mutation wird erstellt
            if (mutation.getFitness() > currentBest.getFitness()) { //Vergleich der Fitnesswerte von mutierter Kopie und urspruenglichem Genotyp
                currentBest = mutation; //Wenn Kopie besser, wird sie der history hinzugefügt und als neuer bester Genotyp fuer weitere Mutationen verwendet
                statistics.addBestIndividual(currentBest);
            }
            else {
                currentBest.survive(); //Wenn nicht wird Alter des urspruenglichen Genotypen erhoeht, da er "ueberlebt" hat
            }
            iterations++; //Durchlauf wird erhoeht
        }
        return currentBest;
    }

}
