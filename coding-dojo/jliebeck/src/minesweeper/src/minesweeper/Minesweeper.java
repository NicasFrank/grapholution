package minesweeper;
import java.io.*;
import java.util.Scanner;

public class Minesweeper {
    private Board board;
    private StreamTokenizer tok;
    private boolean done, quit, win;
    private int lastCell;

    public Minesweeper(int width, int height, int mines) {
        board = new TBoard(width, height, mines);
        done = win = quit = false;
    }
    public void play() throws IOException{
        long startTime = System.currentTimeMillis();
        while (!done) {
            board.draw();
            System.out.print("Command: ");
            System.out.flush();
            tok.nextToken();

            switch (tok.ttype) {
                case StreamTokenizer.TT_WORD:
                     break;
                case StreamTokenizer.TT_EOL:
                     continue;
                case StreamTokenizer.TT_EOF:
                     done = quit = true;
                     break;
            }
            if (board.getUnknown() == board.getMines()) {
                done = win = true;
            }
            if (lastCell == Board.MINE){
                done = true;
            }
            long elapsedTime = System.currentTimeMillis() - startTime;
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    board.reveal(i, j);
                }
            }
            board.draw();
            if (win) {
                System.out.println("Congratulations!");
            }
            if (!quit) {
                System.out.println("GameOver!");
            }
        }
    }
}

