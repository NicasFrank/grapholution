package mineSweeperPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static boolean gameOver = false;
    static MineField mineField;

    public static void main(String[] args) {
        mineField = new MineField(3);
        String userinput = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InputHandler.printHelp();
        while (!gameOver) {
            mineField.displayField(System.out);
            userinput = getConsoleLine(br);
            handleInput(userinput);
        }
    }

    private static String getConsoleLine(BufferedReader br) {
        try {
            return br.readLine();
        } catch(IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void handleInput(String userInput) {
        InputHandler.GameStates gamestate;

        gamestate = InputHandler.handleInput(userInput, mineField);
        if (gamestate == InputHandler.GameStates.Lost) {
            mineField.displayField(System.out);
            System.out.println("You lose!");
            System.exit(0);
        }
        if (gamestate == InputHandler.GameStates.Won) {
            mineField.displayField(System.out);
            System.out.println("You win!");
            System.exit(0);
        }
    }
}
