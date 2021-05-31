package mineSweeperPackage;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class RandomPlacements {

    public static void placeRandomMines(Square[][] squares) {
        TreeSet<Point> placements = new TreeSet<>();

        while (placements.size() < squares.length) {
            placements.add(randomPoint(squares.length));
        }

        int x;
        int y;
        for (Point point: placements) {
            x = point.x;
            y = point.y;
            squares[x][y] = new Square(SquareValues.Bomb);
        }
    }

    private static Point randomPoint(int limit) {
        Random random = new Random();

        int x = random.nextInt(limit);
        int y = random.nextInt(limit);

        return new Point(x, y);
    }
}
