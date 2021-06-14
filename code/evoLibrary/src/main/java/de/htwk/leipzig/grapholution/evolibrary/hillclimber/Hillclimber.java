package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import java.util.ArrayList;
import java.util.Random;

/**
 * Einfacher Hillclimber-Algorithmus mit Bit-Strings als Konfigurationen
 */
public class Hillclimber {
    private ArrayList<String> history;

    /**
     * erzeugt eine Liste der erfolgreichen Evolutionsergebnisse der Konfigurationen
     *      und fügt die Startkonfiguration als erstes Element hinzu
     * @param startConfig erhält eine Startkonfiguration,
     *      sonst wird mit eine Bit-String gestartet der nur Nullen hat
     */
    public Hillclimber(String startConfig) {
        if (!startConfig.matches("[01]+")) {
            startConfig = "00000000";
        }

        history = new ArrayList<>();
        history.add(startConfig);
    }

    /**
     * Liefert die aktuell beste Konfiguration
     *
     * @return letzte Element von history
     */
    private String getLastConfig() {
        return history.get(history.size() - 1);
    }

    /**
     * Führt den Hillclimber-Algorithmus aus und gibt das Ergebnis zurück
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
     * Bewertet eine Konfiguration in dem es die Zahl der einsen zusammenzählt
     * @param configuration erhält eine Konfiguration
     * @return Anzahl der einsen
     */
    private int evaluate(String configuration) {
        int sum = 0;
        for(int i = 0; i<configuration.length(); i++){
            if(configuration.charAt(i) == '1'){
                sum++;
            }
        }
        return sum;
    }

    /**
     * Mutiert die Konfiguration an einer zufälligen Stelle von 1 zu 0 bzw von 0 zu 1
     * @param config erhält eine Konfiguration
     * @return die erhalten Konfiguration mit einer zufällig geflippten Stelle
     */
    private String mutate(String config) {
        Random rand = new Random();
        int index = rand.nextInt(config.length());
        int newBit = 1 - Character.getNumericValue(config.charAt(index));

        return config.substring(0, index) + newBit + config.substring(index + 1);
    }
}
