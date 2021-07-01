package pbeck;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static boolean running = true;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MineField spiel = new MineField(5);
        PlayerInput inputHandler = new PlayerInput();
        String input;
        while(running) {
            spiel.print();
            System.out.print("Koordinaten durch Leerzeichen getrennt eingeben: ");
            input = getUserInput(reader);
            running = inputHandler.makeMove(spiel, input);
        }
        if (spiel.gameWon()) {
            spiel.print();
            System.out.println("Du hast gewonnen!");
            return;
        }
        spiel.print();
        System.out.println("Du bist auf eine Bombe getreten :(");
    }

    private static String getUserInput(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
