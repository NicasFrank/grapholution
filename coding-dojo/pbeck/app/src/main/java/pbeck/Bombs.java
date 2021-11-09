package pbeck;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Bombs {
    private HashSet<Point> positions = new HashSet<>();

    public Bombs(int size) {
        Random random = new Random();
        while (positions.size() < size) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            positions.add(new Point(x,y));
        }
    }

    public void placeBombs(Cell[][] field) {
        for (Point p: positions) {
            int x = p.x;
            int y = p.y;
            field[x][y] = new Cell(true);
        }
    }

}
