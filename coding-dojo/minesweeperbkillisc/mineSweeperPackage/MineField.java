package mineSweeperPackage;

import java.io.PrintStream;

public class MineField {
    private final Square[][] squares;
    private int revealed = 0;

    public MineField(int size) {
        if (size <3) {
            throw new IllegalArgumentException("The size must be bigger than 3");
        }
        squares = new Square[size][size];
        RandomPlacements.placeRandomMines(squares);
    }

    public void displayField(PrintStream printStream) {
        for(int i = 0; i < squares.length; i++) {
            displayArray(i, printStream);
            printStream.println();
        }
    }
    private void displayArray(int index, PrintStream printStream) {
        for(int i = 0; i < squares.length; i++) {
            squares[index][i].display(printStream);
        }
    }

    public boolean allRevealed() {
        return revealed == squares.length*squares.length- squares.length;
    }

    public boolean reveal(int x, int y) {
        if (x >= squares.length || y >= squares.length) {return false;}
        if (squares[x][y].isBomb() == 1) {
            squares[x][y].reveal("*");
            return true;
        }

        revealed++;
        int bombcount = AdjacentSquares.adjecentBombs(squares, x, y);
        if (bombcount == 0) {
            squares[x][y].reveal(".");
            return false;
        }
        String stringvalue = Integer.toString(bombcount);
        squares[x][y].reveal(stringvalue);
        return false;
    }
}