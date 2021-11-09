package eanton;
import java.util.Random;

public class Spielfeld {
    private int x;
    private int y;
    Random zufall = new Random();
    public Feld spielfeld[][];
    private int anzMinen;
    public Spiellogik logik;

    Spielfeld(int dimx, int dimy, int minen) {
        x = dimx;
        y = dimy;
        spielfeld = new Feld[x][y];
        anzMinen = minen;
        aufbau();
        logik = new Spiellogik(this, x, y);
    }

    //gibt aktuellen Spielstand aus
    public void spielstand() {
        for (int i = 0; i < y; i++) {
            for (int h = 0; h < x; h++) {
                System.out.print(spielfeld[h][i].status + " ");
            }
            System.out.println("");
        }
        System.out.println("----------------------------------");
    }

    //erstellt das Spielfeld
    private void aufbau() {

        for (int i = 0; i < y; i++) {
            for (int h = 0; h < x; h++) {
                spielfeld[h][i] = new Feld();
            }
        }
        mineOderLeer();
    }

    //platziert zufaellig im Konstruktor übergebene Anzahl Minen
    private void mineOderLeer() {
        int zufallX;
        int zufallY;

        while (anzMinen >= 0) {
            zufallX = zufall.nextInt(x);
            zufallY = zufall.nextInt(y);
            if (spielfeld[zufallX][zufallY].art == false) {
                spielfeld[zufallX][zufallY].art = true;
                anzMinen--;
            }
        }
    }
}