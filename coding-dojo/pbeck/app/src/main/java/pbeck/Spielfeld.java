package pbeck;

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
    }

    public void move(int x, int y) {

    }

}
