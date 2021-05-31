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

    public void discoverThis(){
        this.discovered = true;
    }

    public void drawThis(){
        if(!discovered){
            System.out.print("#");
        }
    }

}
