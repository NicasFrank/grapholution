package minesweeper;
import java.util.Random;

public abstract class Board {
    protected int width, height;
    protected int numMines;
    protected int numMarked;
    protected int numUnknown;
    protected boolean[][] mines;
    protected int[][] board;

    public static final int UNKNOWN = -1;
    public static final int MARKED = -2;
    public static final int MINE = -3;

    public Board(int width, int height, int numMines) {
        this.width = width;
        this.height = height;
        this.numMines = numMines;
        this.numMarked = 0;
        this.numUnknown = width * height;
        mines = new boolean[width][height];
        board = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mines[i][j] = false;
                board[i][j] = UNKNOWN;
            }
        }

        int cells = width * height;
        int temp = 0;
        Random r = new Random();

        while (temp < numMines) {
            int cell = r.nextInt();
            cell = (cell < 0 ? -cell : cell) % cells;
            if (!mines[cell % width][cell / width]) {
                mines[cell % width][cell / width] = true;
                temp++;
            }
        }
    }

    public abstract void draw();{
    }

    public int reveal(int x, int y) {
        switch (board[x][y]) {
            case MARKED:
                numMarked--;
            case UNKNOWN:
                numUnknown--;
                if (mines[x][y]) {
                    board[x][y] = MINE;
                }
                board[x][y] = closeMines(x, y);
        }
        return board[x][y];
    }

    public void revealMore(int x, int y) {
        int minx, miny, maxx, maxy;
        int result = 0;
        minx = (x <= 0 ? 0 : x - 1);
        miny = (y <= 0 ? 0 : y - 1);
        maxx = (x >= width - 1 ? width : x + 2);
        maxy = (y >= height - 1 ? height : y + 2);

        for (int i = minx; i < maxx; i++) {
            for (int j = miny; j < maxy; j++) {
                if (!mines[i][j] && board[i][j] == UNKNOWN) {
                    reveal(i, j);
                    if (board[i][j] == 0) {
                        revealMore(i, j);
                    }
                }
            }
        }
    }

    public boolean unmark(int x, int y) {
        if (board[x][y] == MARKED) {
            board[x][y] = UNKNOWN;
            numMarked--;
            return true;
        }
        return false;
    }

    private int closeMines(int x, int y){
        int minx, miny, maxx , maxy;
        int result = 0;

        minx = (x <= 0 ? 0 : x - 1);
        miny = (y <= 0 ? 0 : y - 1);
        maxx = (x >= width - 1 ? width : x + 2);
        maxy = (y >= height - 1 ? height : y + 2);
        for (int i = minx; i < maxx; i++) {
            for (int j = miny; j < maxy; j++) {
                if (mines[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getMines(){
        return numMines;
    }
    public int getMarked(){
        return numMarked;
    }
    public int getUnknown(){
        return numUnknown;
    }
}
