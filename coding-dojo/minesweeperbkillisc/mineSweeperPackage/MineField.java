package mineSweeperPackage;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MineField {
    private final Square[][] squares;

    public MineField(int size) {
        if (size <3) {
            throw new IllegalArgumentException("The size must be bigger than 3");
        }
        squares = new Square[size][size];

        for (int i = 0; i < size; i++) {
            fillArray(i);
        }

        RandomPlacements.placeRandomMines(squares);
    }

    private void fillArray(int index) {
        for (int i = 0; i < squares.length; i++) {
            squares[index][i] = new Square(SquareValues.Empty);
        }
    }

    public void reveal(int x, int y) {
        if (squares[x][y].isBomb()== 1) {
            squares[x][y].reveal("*");
            return;
        }

        int bombcount = AdjacentSquares.adjecentBombs(squares, x, y);

        if (bombcount == 0) {
            squares[x][y].reveal(".");
            return;
        }

        String stringvalue = Integer.toString(bombcount);
        squares[x][y].reveal(stringvalue);
    }
}