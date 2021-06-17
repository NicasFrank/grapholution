package de.htwk.leipzig.grapholution.evolibrary.EvoAlgAbstract;

/**
 * Das Grundgerüst jedes evolutionären Algorithmus, mit generischem Parameter T für Flexibilität
 * beim Typ der Konfiguration. Die Methode run führt den entsprechenden Algorithmus auf eine
 * Startkonfiguration vom Typ T auf und gibt das Ergebnis des Algorithmus zurück.
 *
 * @param <T> der Typ der Konfiguration
 */
public interface IEvoAlg<T> {
    /**
     * Führt den Algorithmus auf die übergebene Startkonfiguration aus und gibt das Ergebnis zurück.
     *
     * @param startConfig Die Startkonfiguration
     * @return Das Ergebnis des Algorithmus
     */
    public T run(T startConfig);
}