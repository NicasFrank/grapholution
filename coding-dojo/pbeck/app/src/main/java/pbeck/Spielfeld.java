package pbeck;

interface

public class Spielfeld {
    private int size;
    private String[] feld;
    private Bombs bombs;

    public Spielfeld() {
        this.size = 10;
        feld = new String[size * size];
        for(String e: feld) {
            e = "#";
        }
        bombs = new Bombs();
    }

    public void zug(int position) {
        if(!bombs.isBomb(position)) {
            printZahl(position);
        }
    }

    public void printZahl(int Position) {
        int proximity = 0;
        if(position )
    }

}
