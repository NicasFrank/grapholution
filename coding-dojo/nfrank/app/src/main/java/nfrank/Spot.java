package nfrank;

import java.util.Random;

public class Spot {

    private boolean mine;
    private boolean discovered = false;
    private int value;
    private Random random = new Random();

    public Spot(){
        this.mine = random.nextBoolean();
    }

    public boolean discoverThis(int around){
        if(mine){
            return true;
        }
        this.discovered = true;
        this.value = around;
        return false;
    }

    public void drawThis(){
        if(!discovered){
            System.out.print("#");
            return;
        }
        System.out.print(value);
    }

    public void mineIt(int around){
        if(mine){
            around++;
        }
    }

}
