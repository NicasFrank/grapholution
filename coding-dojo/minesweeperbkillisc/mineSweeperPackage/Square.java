package mineSweeperPackage;

public class Square {
    private final SquareValues value;
    private String displayValue;

    public Square(SquareValues value){
        displayValue = "#";
        this.value = value;
    }

    public int isBomb() {
        if (value == SquareValues.Bomb) {
            return 1;
        }

        return 0;
    }

    public void reveal (String displayValue) {
        this.displayValue = displayValue;
    }

    public void display() {
        System.out.print(displayValue);
    }
}