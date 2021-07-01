package eanton;

public class Spiellogik {
    private Spielfeld spielfeldObjekt;
    public boolean ende;
    private int x;
    private int y;

    Spiellogik(Spielfeld sf, int dimX, int dimY) {
        spielfeldObjekt = sf;
        ende = false;
        x = dimX;
        y = dimY;
    }

    //Prueft, ob augewaehltes Feld Mine ist
    public void check(int tryX, int tryY) {
        if(spielfeldObjekt.spielfeld[tryX][tryY].art) {
            ende = true;
            System.out.println("BOOM -  leider verloren");
        }
        if(!spielfeldObjekt.spielfeld[tryX][tryY].art) {
            nachbarMinen(tryX, tryY);
        }
    }

    //bestimmt Anzahl Nachbarminen von dem Feld an den uebergebenen Koordinaten. Habe return 0 benutzt, um aus Funktion rauszuspringen
    private int nachbarMinen(int tryX, int tryY){
        int anzahlNachbarMinen = 0;

        //zu behandelndes Feld wurde schon behandelt
        if(spielfeldObjekt.spielfeld[tryX][tryY].status != "#") {
            return 0;
        }

        //normaler Fall
        anzahlNachbarMinen = feldumrundung(tryX, tryY, true);

        if(anzahlNachbarMinen == 0) { //Das hier ist leider erst bekannt, wenn einmal alle Nachbarn angeschaut wurden. Es werden alle Nachbarn des Feldes aufgedeckt und geprueft
            feldumrundung(tryX, tryY, false);
        }
        return 0;
    }

    //durchlaeuft alle 8 Felder um ein Feld herum (habe es leider nicht mit weniger Einrückungstiefe hinbekommen.. dickes sorry)
    private int feldumrundung(int tryX, int tryY, boolean fall) { //fall: true = anzahl Minen bestimmen, false = umliegende Felder aufdecken
        int anzahlNachbarMinen = 0;
        for (int i = tryY-1; i < tryY+2; i++) {
            for (int h = tryX - 1; h < tryX+2; h++) {
                if (!(h == tryX && i == tryY) && i >= 0 && h >= 0 && i < y && h < x) { //eigenes Feld und out of bounds wir nicht beachtet

                    if (fall) { //anzahl Minen bestimmen
                        anzahlNachbarMinen = anzahlNachbarMinen + feldArt(h, i);
                        spielfeldObjekt.spielfeld[tryX][tryY].status = Integer.toString(anzahlNachbarMinen);
                    }

                    if (!fall) { //umliegende Felder aufdecken
                        nachbarMinen(h,i);
                    }
                }
            }
        }
        return anzahlNachbarMinen;
    }

    //gibt zurueck ob bestimmtes Feld eine Mine hat oder nicht (Kommentare für Debug)
    private int feldArt(int h, int i) {
        if(spielfeldObjekt.spielfeld[h][i].art) {
            //System.out.println(h+" "+ i + " ist Mine");
            return 1;
        }
        //System.out.println(h+" "+ i + " ist keine Mine");
        return 0;
    }
}