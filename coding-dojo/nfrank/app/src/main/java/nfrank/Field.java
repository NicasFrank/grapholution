package nfrank;

public class Field {

    private final Spot[][] field = new Spot[3][3];
    private int around;
    private boolean gamestate;

    public Field (){
        gamestate = false;
        fillRow();
    }

    public boolean checkBoard(){
        for(int i = 0; i< 3; i++){
            for(int j = 0; j<3; j++){
                gamestate = false;
            }
        }
        return gamestate;
    }

    public boolean discoverSpot(int x, int y){
        if(field[x][y].getDiscovered()){
            return gamestate;
        }
        checkAround(x, y);
        gamestate = field[x][y].discoverThis(this.around);
        if(this.around == 0){
            revealAround(x,y);
        }
        checkMines(x,y);
        return gamestate;
    }

    private void checkMines(int x, int y){
        if(x%2 == 0 && y%2 == 0 && around == 2){
            revealMines(x,y);
        }
        if(x%2 == 1 && y%2 == 1 && around == 4){
            revealMines(x,y);
        }
        if((x%2 == 0 && y%2 == 1 || x%2 == 1 && y%2 == 0) && around == 3){
            revealMines(x,y);
        }
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
        switch (y) {
            case 0, 2 -> discoverSpot(x, 1);
            case 1 -> {
                discoverSpot(x, 2);
                discoverSpot(x, 0);
            }
        }
    }

    private void revealMines(int x, int y){
        switch (x) {
            case 0, 2 -> field[1][y].revealSpot();
            case 1 -> {
                field[2][y].revealSpot();
                field[0][y].revealSpot();
            }
        }
        switch (y) {
            case 0, 2 -> field[x][1].revealSpot();
            case 1 -> {
                field[x][2].revealSpot();
                field[x][0].revealSpot();
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
