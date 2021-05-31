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
                if(field[x+1][y].mineIt()){
                    around++;
                }
                break;
            case 1:
                if (field[x+1][y].mineIt()) {
                    around++;
                }
                if (field[x-1][y].mineIt()) {
                    around++;
                }
                break;
            case 2:
                if (field[x-1][y].mineIt()) {
                    around++;
                }
                break;
        }
        switch(y){
            case 0:
                if(field[x][y+1].mineIt()){
                    around++;
                }
                break;
            case 1:
                if (field[x][y+1].mineIt()) {
                    around++;
                }
                if (field[x][y-1].mineIt()) {
                    around++;
                }
                break;
            case 2:
                if (field[x][y-1].mineIt()) {
                    around++;
                }
                break;
        }
        if(around == 0){
            revealAround(x,y);
        }
        return around;
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
