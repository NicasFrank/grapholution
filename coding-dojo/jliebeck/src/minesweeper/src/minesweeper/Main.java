package minesweeper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Minesweeper minesweeper;

        if (args.length < 3) {
            System.out.println("Usage: java MineSweeper width height mines");
            System.exit(0);
        }
        if(args.length >= 3){
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            int mines = Integer.parseInt(args[2]);
            minesweeper = new Minesweeper(width, height, mines);
            minesweeper.play();
        }
    }
}
