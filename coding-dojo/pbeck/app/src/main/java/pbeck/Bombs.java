package pbeck;

import java.util.Random;

public class Bombs {
    private int[] positions;

    public Bombs() {
        Random zufall = new Random();
        positions = new int[10];
        for(int position: positions) {
            position = zufall.nextInt(100);
        }
    }

    public boolean isBomb(int position) {
        boolean bomb = false;
        for(int pos: positions) {
            bomb ^= pos == position;
        }
        return bomb;
    }

}
