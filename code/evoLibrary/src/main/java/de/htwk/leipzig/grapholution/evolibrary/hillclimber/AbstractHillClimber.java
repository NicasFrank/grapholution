package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import de.htwk.leipzig.grapholution.evolibrary.EvoAlgAbstract.IEvaluator;
import de.htwk.leipzig.grapholution.evolibrary.EvoAlgAbstract.IEvoAlg;
import java.util.ArrayList;

/**
 * Das abstrakte Grundgerüst jedes Hillclimbers, unabhängig vom Typ der Konfiguration. Implementiert
 * die run Methode des Interfaces IEvoAlg, da diese unabhängig vom Typ der Konfiguration ist.
 * Konkrete Hillclimber-Implementierungen müssen nur noch die Methoden evaluate, mutate und
 * stoppingCondition implementieren.
 *
 * @param <T> der Typ der Konfiguration
 */
public abstract class AbstractHillClimber<T, V extends Comparable<V>> implements IEvoAlg<T> {
    protected final ArrayList<T> history;

    public IEvaluator<T, V> getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(IEvaluator<T, V> evaluator) {
        this.evaluator = evaluator;
    }

    private IEvaluator<T, V> evaluator;

    /**
     * Erzeugt eine Liste der erfolgreichen Evolutionsergebnisse der Konfigurationen
     */
    public AbstractHillClimber(IEvaluator<T, V> evaluator) {
        history = new ArrayList<>();
        this.evaluator = evaluator;
    }

    /**
     * Wendet den Hillclimber-Algorithmus auf eine Startkonfiguration an
     * und gibt das Ergebnis zurück.
     *
     * @param startConfig die Startkonfiguration
     * @return gibt das Ergebnis des Hillclimber-Algorithmus
     */
    @Override
    public T run(T startConfig) {
        history.add(startConfig);

        while (!stoppingCondition(getLastConfig())) {
            T temp = mutate(getLastConfig());
            V eval = evaluator.evalute(temp);

            if (eval.compareTo(evaluator.evalute(getLastConfig())) > 1) {
                history.add(temp);
            }
        }

        return getLastConfig();
    }

    /**
     * Liefert die aktuell beste Konfiguration, also das letzte Element von history
     *
     * @return die aktuell beste Konfiguration
     */
    protected T getLastConfig() {
        return history.get(history.size() - 1);
    }

    /**
     * Gibt zurück, ob die übergeben Konfiguration die Abbruchbedingung erfüllt.
     * Wird immer auf die getLastConfig angewendet, um zu entscheiden, wann der
     * hillclimbing-Prozess angehalten werden soll.
     *
     * @see this.getLastConfig()
     * @param config Die zu überprüfende Konfiguration, hier immer this.getLastConfig()
     * @return Ob die Abbruchbedingung erfüllt ist oder nicht
     */
    protected abstract boolean stoppingCondition(T config);

    /**
     * Mutiert die übergebene Konfiguration
     *
     * @param config die zu mutierende Konfiguration
     * @return die mutierte Konfiguration
     */
    public abstract T mutate(T config);
}
