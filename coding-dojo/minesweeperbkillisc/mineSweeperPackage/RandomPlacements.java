package mineSweeperPackage;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomPlacements {

    public static void placeRandomMines(Square[][] squares) {
        Set<Point> placements = new HashSet<Point>();

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

        for (int i = 0; i < squares.length; i++) {
            fillArray(i, squares);
        }
    }

    private static void fillArray(int index, Square[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            setEmptyIfNull(index, i, squares);
        }
    }

    private static void setEmptyIfNull(int x, int y, Square[][] squares) {
        if (squares[x][y] == null) {
            squares[x][y] = new Square(SquareValues.Empty);
        }
    }

    private static Point randomPoint(int limit) {
        Random random = new Random();

        int x = random.nextInt(limit);
        int y = random.nextInt(limit);

        return new Point(x, y);
    }
}