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

    private final List<Genotype<T>> history; //Alle Genotypen, welche Verbesserungen brachten
    private final Mutator<T> mutator;

    /**
     * Konstruktor zur Erstellung eines einfachen Hillclimbers mit einstellbarer Mutation und Genotyp
     * @param genotype Genotyp auf dem der Algorithmus arbeiten soll
     * @param mutator Mutation, die zur Veraenderung des Genotypen benutzt werden soll
     */
    public Hillclimber(Genotype<T> genotype, Mutator<T> mutator) {
        super(genotype);
        this.mutator = mutator;
        history = new ArrayList<>();
        history.add(genotype);
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
        history = new ArrayList<>();
        history.add(genotype);
    }

    /**
     * Getter fuer den aktuellsten Genotyp
     * @return Genotyp mit bestem Fitnesswert
     */
    private Genotype<T> getLastConfig() {
        return history.get(history.size() - 1);
    }

    /**
     * Funktion zum Ausfuehren des Hillclimbers
     * @return Bester erreichter Genotyp
     */
    public Genotype<T> run() {
            while (getLastConfig().getFitness() < getLastConfig().MAX_FITNESS_VALUE && //Ueberprufung ob Limit oder bestmoeglicher Genotyp bereits erreicht
                (limit < 0 || iterations < limit)){
            Genotype<T> copy = getLastConfig().createCopy(); //Kopie des letzten Genotypen zur Mutation wird erstellt
            mutator.mutate(copy); //Kopie wird mutiert
            if (copy.getFitness() > getLastConfig().getFitness()) { //Vergleich der Fitnesswerte von mutierter Kopie und urspruenglichem Genotyp
                history.add(copy); //Wenn Kopie besser, wird sie der history hinzugefügt und als neuer bester Genotyp fuer weitere Mutationen verwendet
            }
            else {
                getLastConfig().survive(); //Wenn nicht wird Alter des urspruenglichen Genotypen erhoeht, da er "ueberlebt" hat
            }
            iterations++; //Durchlauf wird erhoeht
        }
        return getLastConfig();
    }

}
