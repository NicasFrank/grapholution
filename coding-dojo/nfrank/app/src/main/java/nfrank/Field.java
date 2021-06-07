package nfrank;

public class Field {

    private final Spot[][] field = new Spot[3][3];
    private int around;

    public Field (){
        fillRow();
    }

    public boolean discoverSpot(int x, int y){
        checkAround(x, y);
        return field[x][y].discoverThis(this.around);
    }

    private void checkAround(int x, int y){
        this.around = 0;
        switch (x) {
            case 0, 2 -> field[1][y].mineIt(this);
            case 1 -> {
                field[2][y].mineIt(this);
                field[0][y].mineIt(this);
            }
        }
        switch (y) {
            case 0, 2 -> field[x][1].mineIt(this);
            case 1 -> {
                field[x][2].mineIt(this);
                field[x][0].mineIt(this);
            }
        }
        if(this.around == 0){
            revealAround(x,y);
        }
    }

    public void incrementAround(){
        this.around++;
    }

    private void revealAround(int x, int y){

        switch (x) {
            case 0, 2 -> discoverSpot(1, y);
            case 1 -> {
                discoverSpot(2, y);
                discoverSpot(0, y);
            }
        }
        switch (x) {
            case 0, 2 -> discoverSpot(x, 1);
            case 1 -> {
                discoverSpot(x, 2);
                discoverSpot(x, 0);
            }
        }

    }

    public void drawRow(){
        for(int i = 0; i<3; ++i){
            drawColumn(i);
            System.out.print("\n");
        }
    }

    private void drawColumn(int count){
        for(int i = 0; i<3; ++i){
            field[i][count].drawThis();
        }
    }

    private void fillRow(){
        for(int i = 0; i<3; ++i){
            fillColumn(i);
        }
    }

    private void fillColumn(int count){
        for(int i = 0; i<3; ++i){
            field[count][i] = new Spot();
        }
    }
}
