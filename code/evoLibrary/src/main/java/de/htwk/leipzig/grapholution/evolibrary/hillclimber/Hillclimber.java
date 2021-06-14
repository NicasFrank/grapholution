package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import java.util.ArrayList;

/**
 * Einfacher Hillclimber-Algorithmus mit Bit-Strings als Konfigurationen
 */
public class Hillclimber {
    private ArrayList<String> history;

    public Hillclimber(String startConfig) {
        if (!startConfig.matches("[01]+")) {
            startConfig = "00000000";
        }

        history = new ArrayList<>();
        history.add(startConfig);
    }

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

    private int evaluate(String configuration) {
        return 0;
    }

    private String mutate(String configuration) {
        return "";
    }
}
