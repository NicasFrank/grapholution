package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import java.util.Random;

/**
 * Beispielimplementation des Hillclimbers mit int-Array als Konfiguration
 */
public class IntArrayHillclimber extends AbstractHillClimber<int[]> {

    /**
     * Bricht das Hillclimbing ab, falls die aktuelle Konfiguration sortiert ist
     *
     * @param config Die zu überprüfende Konfiguration
     * @return ob Konfiguration sortiert ist oder nicht
     */
    @Override
    protected boolean stoppingCondition(int[] config) {
        return evaluate(config) == maxInverseCount(config.length);
    }

    /**
     * Bewertet die übergebene Konfiguration mithilfe der Inversionszahl.
     * Damit die Bewertung bei besserer Sortierung steigt, wird diese von der maximal
     * möglichen Inversionszahl abgezogen
     *
     * @param config die zu bewertende Konfiguration
     * @return die Bewertung der Konfiguration
     */
    @Override
    public int evaluate(int[] config) {
        int inverseCount = 0;

        for (int i = 0; i < config.length - 1; i++) {
            for (int j = i + 1; j < config.length; j++) {
                if (config[i] > config[j]) {
                    inverseCount++;
                }
            }
        }

        return maxInverseCount(config.length) - inverseCount;
    }

    /**
     * Berechnet das Maximum der Inversionszahl eines Arrays der Länge n.
     * Formel: i = Summe(1, n - 1)
     * Dies wird mithilfe der Gaußschen Summenformel umgesetz,
     *
     * @param n die Länge des arrays
     * @return das Maximum der Inversionszahl
     */
    private int maxInverseCount(int n) {
        n--;
        return n * (n + 1) / 2;
    }

    /**
     * Mutiert die übergebene Konfiguration, indem zwei zufällige Zahlen vertauscht werden
     *
     * @param config die zu mutierende Konfiguration
     * @return die mutierte Konfiguration
     */
    @Override
    public int[] mutate(int[] config) {
        int[] tempconfig = config.clone();
        Random random = new Random();
        int num1 = random.nextInt(config.length);
        int num2 = random.nextInt(config.length);

        int temp = tempconfig[num1];
        tempconfig[num1] = tempconfig[num2];
        tempconfig[num2] = temp;

        return tempconfig;
    }
}
