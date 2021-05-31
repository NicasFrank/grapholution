package nfrank;

public class Field {

    private Spot[][] field = new Spot[3][3];

    public Field (){
        fillRow();
    }

    public void drawRow(){
        for(int i = 0; i<3; ++i){
            drawColumn(i);
            System.out.print("\n");
        }
    }

    private void drawColumn(int count){
        for(int i = 0; i<3; ++i){
            field[count][i].drawThis();
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
