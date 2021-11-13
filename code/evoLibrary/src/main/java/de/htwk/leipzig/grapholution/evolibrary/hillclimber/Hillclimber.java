package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Einfacher Hillclimber-Algorithmus mit Bit-Strings als Konfigurationen.
 */
public class Hillclimber {

    private final List<String> history;

    /**
     * erzeugt eine Liste der erfolgreichen Evolutionsergebnisse der Konfigurationen
     *      und fügt die Startkonfiguration als erstes Element hinzu.
     * @param startConfig erhält eine Startkonfiguration,
     * @throws IllegalArgumentException falls der übergebene String null ist oder kein Bitstring ist
     */
    public Hillclimber(String startConfig) {
        if (startConfig == null || !startConfig.matches("[01]+")) {
            throw new IllegalArgumentException("Ungueltiger Bitstring eingegeben!");
        }

        history = new ArrayList<>();
        history.add(startConfig);
    }

    /**
     * Liefert die aktuell beste Konfiguration.
     *
     * @return letzte Element von history
     */
    private String getLastConfig() {
        return history.get(history.size() - 1);
    }

    /**
     * Führt den Hillclimber-Algorithmus aus und gibt das Ergebnis zurück.
     *
     * @return gibt das Ergebnis des Hillclimber-Algorithmus
     */
    public String hillclimb() {
        while (evaluate(getLastConfig()) < getLastConfig().length()) {
            String temp = mutate(getLastConfig());
            int eval = evaluate(temp);

            if (eval > evaluate(getLastConfig())) {
                history.add(temp);
            }
        }

        return getLastConfig();
    }

    /**
     * Bewertet eine Konfiguration in dem es die Zahl der Einsen zusammenzählt.
     * @param configuration erhält eine Konfiguration
     * @return Anzahl der Einsen
     */

    public int evaluate(String configuration) {
        int sum = 0;
        for (int i = 0; i < configuration.length(); i++) {
            if (configuration.charAt(i) == '1') {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Mutiert die Konfiguration an einer zufälligen Stelle von 1 zu 0 bzw von 0 zu 1.
     * @param config erhält eine Konfiguration
     * @return die mutierte Konfiguration mit einer zufällig geflippten Stelle
     */
    public String mutate(String config) {
        int index = ThreadLocalRandom.current().nextInt(config.length());
        int newBit = 1 - Character.getNumericValue(config.charAt(index));

        return config.substring(0, index) + newBit + config.substring(index + 1);
    }
}
