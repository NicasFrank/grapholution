package pbeck;

import java.util.ArrayList;

public class BombCounter {

    private ArrayList<Cell> adjacent;

    public BombCounter() {
        adjacent = new ArrayList<>();
    }

    public int countBombs(Cell[][] field, int x, int y) {
        add(field, x - 1, y);
        add(field, x + 1, y);
        add(field, x, y - 1);
        add(field, x, y + 1);
        add(field, x - 1, y - 1);
        add(field, x - 1, y + 1);
        add(field, x + 1, y - 1);
        add(field, x + 1, y + 1);

        int count = 0;

        for (Cell c : adjacent) {
            count += c.value();
        }
        return count;
    }

    private void add(Cell[][] field, int x, int y) {
        if (x < 0 || x >= field.length) {
            return;
        }
        if (y < 0 || y >= field.length) {
            return;
        }
        adjacent.add(field[x][y]);
    }
}
