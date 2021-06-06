package pbeck;


public class Cell {
    private boolean isBomb;
    private String view;

    public Cell(boolean isBomb) {
        this.isBomb = isBomb;
        view = "#";
    }

    public int value() {
        return isBomb ? 1 : 0;
    }

    public void reveal(int adjacent){
        String notBombView = (adjacent == 0 ? "." : "" + adjacent);
        view = isBomb ? "$" : notBombView;
    }

    public void out() {
        System.out.print(view + " ");
    }
}
