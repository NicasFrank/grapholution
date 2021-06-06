package pbeck;

public class PlayerInput {

    public boolean makeMove(MineField field, String input) {
        String[] position = input.split("\\s");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        return field.reveal(x, y);
    }
}
