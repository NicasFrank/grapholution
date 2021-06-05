package nfrank;

public class Field {

    private Spot[][] field = new Spot[3][3];
    private int around;

    public Field (){
        fillRow();
    }

    public boolean discoverSpot(int x, int y){
        checkAround(x, y);
        if(field[x][y].discoverThis(this.around)){
            return true;
        }
        return false;
    }

    private void checkAround(int x, int y){
        this.around = 0;
        switch(x){
            case 0:
                field[x+1][y].mineIt(this);
                break;
            case 1:
                field[x+1][y].mineIt(this);
                field[x-1][y].mineIt(this);
                break;
            case 2:
                field[x-1][y].mineIt(this);
                break;
        }
        switch(y){
            case 0:
                field[x][y+1].mineIt(this);
                break;
            case 1:
                field[x][y+1].mineIt(this);
                field[x][y-1].mineIt(this);
                break;
            case 2:
                field[x][y-1].mineIt(this);
                break;
        }
        if(this.around == 0){
            revealAround(x,y);
        }
    }

    public void incrementAround(){
        this.around++;
    }

    private void revealAround(int x, int y){

        switch(x){
            case 0:
                discoverSpot(x+1,y);
                break;
            case 1:
                discoverSpot(x+1,y);
                discoverSpot(x-1,y);
                break;
            case 2:
                discoverSpot(x-1,y);
                break;
        }
        switch(x){
            case 0:
                discoverSpot(x,y+1);
                break;
            case 1:
                discoverSpot(x,y+1);
                discoverSpot(x,y-1);
                break;
            case 2:
                discoverSpot(x,y-1);
                break;
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
