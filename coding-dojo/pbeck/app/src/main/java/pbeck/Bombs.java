package pbeck;

public class Bombs {
    private int[] positions;

    public Bombs(int size) {

    }

    public boolean isBomb(int position) {
        boolean bomb = false;
        for(int pos: positions) {
            bomb ^= pos == position;
        }
        return bomb;
    }

}
