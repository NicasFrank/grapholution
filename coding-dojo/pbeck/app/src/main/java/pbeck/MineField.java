package pbeck;

public class MineField {
    private int size;
    private Cell[][] field;
    private int revealed;

    public MineField(int size) {
        this.size = size;
        field = new Cell[size][size];
        Bombs bombs = new Bombs(size);
        for (Cell[] line : field) {
            initLine(line);
        }
        bombs.placeBombs(field);
    }

    public void initLine(Cell[] line){
        for(int i = 0; i < line.length; i++){
            line[i] = new Cell(false);
        }
    }

    public boolean reveal(int x, int y) {
        if(x >= field.length || y >= field.length) {return true;}
        revealed++;
        BombCounter counter = new BombCounter();
        int bombNumber = counter.countBombs(field, x, y);
        field[x][y].reveal(bombNumber);
        if (field[x][y].value() == 1 || gameWon()){return false;}
        return true;
    }

    public boolean gameWon() {
        return revealed == field.length * field.length - size;
    }

    public void print() {
        for (int i = 0; i < field.length; i++) {
            println(i);
            System.out.print('\n');
        }
    }

    private void println(int line) {
        for (int i = 0; i < field[line].length; i++) {
            field[line][i].out();
        }
    }
}
