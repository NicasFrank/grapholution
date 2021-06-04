package mineSweeperPackage;

import java.util.ArrayList;

public class AdjacentSquares {

    public static int adjecentBombs(Square[][] squares, int x, int y) {
        ArrayList<Square> adjacentSquares = new ArrayList<>();

        addSquare(squares, adjacentSquares,x + 1, y);
        addSquare(squares, adjacentSquares,x + 1, y + 1);
        addSquare(squares, adjacentSquares,x + 1, y - 1);
        addSquare(squares, adjacentSquares,x - 1, y);
        addSquare(squares, adjacentSquares,x - 1, y + 1);
        addSquare(squares, adjacentSquares,x - 1, y - 1);
        addSquare(squares, adjacentSquares, x , y + 1);
        addSquare(squares, adjacentSquares, x, y - 1);

        int bombCount = 0;

        for (Square square: adjacentSquares) {
            bombCount = bombCount + square.isBomb();
        }

        return bombCount;
    }

    private static void addSquare(Square[][] squares, ArrayList<Square> adjacentSquares, int x, int y) {
        if (x == -1 || x == squares.length) {
            return;
        }
        if (y == -1 || y == squares.length) {
            return;
        }

        adjacentSquares.add(squares[x][y]);
    }
}
