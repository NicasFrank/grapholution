package mineSweeperPackage;

import java.util.ArrayList;

public class AdjacentSquares {

    public static int adjecentBombs(Square[][] squares, int x, int y) {
        ArrayList<Square> adjacentSquares = new ArrayList<>();

        adjacentSquares.add(checkSquare(squares,x + 1, y));
        adjacentSquares.add(checkSquare(squares,x + 1, y + 1));
        adjacentSquares.add(checkSquare(squares,x + 1, y - 1));
        adjacentSquares.add(checkSquare(squares,x - 1, y));
        adjacentSquares.add(checkSquare(squares,x - 1, y + 1));
        adjacentSquares.add(checkSquare(squares,x - 1, y - 1));
        adjacentSquares.add(checkSquare(squares,x , y + 1));
        adjacentSquares.add(checkSquare(squares,x, y - 1));

        adjacentSquares.remove(null);

        int bombCount = 0;

        for (Square square: adjacentSquares) {
            bombCount = bombCount + square.isBomb();
        }

        return bombCount;
    }

    private static Square checkSquare(Square[][] squares, int x, int y) {
        if (x == -1 || x == squares.length) {
            return null;
        }
        if (y == -1 || y == squares.length) {
            return null;
        }

        return squares[x][y];
    }
}
