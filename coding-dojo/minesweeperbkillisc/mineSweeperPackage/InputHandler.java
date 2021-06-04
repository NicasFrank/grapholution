package mineSweeperPackage;

public class InputHandler {
    public enum GameStates {
        Running,
        Lost,
        Won
    }

    public static void printHelp() {
        System.out.println("Help:");
        System.out.println("Enter (x, y) to reveal the square at x, y");
        System.out.println("Enter q to quit the game");
        System.out.println("Enter h to display this help message again");
    }

    public static GameStates handleInput(String input, MineField mineField) {
        if (input.matches("\\(\\d+,\\s*\\d+\\)")) {
            input = input.replaceAll("[(),]", " ");
            input = input.trim();
            String[] coords = input.split("\\s+");

            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            if (mineField.reveal(x, y)) {
                return GameStates.Lost;
            }
            if (mineField.allRevealed()) {
                return GameStates.Won;
            }
            return GameStates.Running;
        }
        if (input.equals("q")) {
            System.exit(0);
        }
        if (input.equals("h")) {
            printHelp();
        }
        return GameStates.Running;
    }
}