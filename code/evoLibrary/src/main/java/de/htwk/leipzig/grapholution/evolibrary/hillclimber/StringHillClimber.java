package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import de.htwk.leipzig.grapholution.evolibrary.EvoAlgAbstract.IEvaluator;

import java.util.Random;

/**
 * Einfacher Hillclimber mit Bit-Strings als Konfigurationen
 */
public class StringHillClimber extends AbstractHillClimber<String, Integer> {
    public StringHillClimber() {
        super(config -> {
            int sum = 0;

            for(int i = 0; i<config.length(); i++){
                if(config.charAt(i) == '1'){
                    sum++;
                }
            }
            return sum;
        });
    }

    /**
     * Bricht das Hillclimbing ab, falls die aktuelle Konfiguration nur aus Einsen besteht
     *
     * @param config Die zu überprüfende Konfiguration
     * @return ob Konfiguration nur aus Einsen besteht oder nicht
     */
    @Override
    protected boolean stoppingCondition(String config) {
        return getEvaluator().evalute(config) == config.length();
    }

    /**
     * Mutiert die übergebene Konfiguration, indem ein zufälliges Bit gekippt wird
     *
     * @param config die zu mutierende Konfiguration
     * @return die mutierte Konfiguration
     */
    @Override
    public String mutate(String config) {
        Random rand = new Random();
        int index = rand.nextInt(config.length());
        int newBit = 1 - Character.getNumericValue(config.charAt(index));

        return config.substring(0, index) + newBit + config.substring(index + 1);
    }
}
