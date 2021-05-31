package nfrank;

public class Field {

    private Spot[][] field = new Spot[3][3];

    public Field (){
        fillRow();
    }

    public boolean discoverSpot(int x, int y){
        int around = checkAround(x, y);
        if(field[x][y].discoverThis(around)){
            return true;
        }
        return false;
    }

    private int checkAround(int x, int y){
        int around = 0;
        switch(x){
            case 0:
                field[x+1][y].mineIt(around);
                break;
            case 1:
                field[x+1][y].mineIt(around);
                field[x-1][y].mineIt(around);
                break;
            case 2:
                field[x-1][y].mineIt(around);
                break;
        }
        switch(y){
            case 0:
                field[x][y+1].mineIt(around);
                break;
            case 1:
                field[x][y+1].mineIt(around);
                field[x][y-1].mineIt(around);
                break;
            case 2:
                field[x][y-1].mineIt(around);
                break;
        }
        return around;
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
