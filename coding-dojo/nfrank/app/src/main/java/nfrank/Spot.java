package nfrank;

import java.util.Random;

public class Spot {

    private final boolean mine;
    private boolean discovered = false;
    private int value;

    public Spot(){
        Random random = new Random();
        this.mine = random.nextBoolean();
    }

    public boolean discoverThis(int around){
        if(mine){
            this.discovered = true;
            return true;
        }
        this.discovered = true;
        this.value = around;
        return false;
    }

    public boolean getDiscovered(){
        return discovered;
    }

    public void revealSpot(){
        this.discovered = true;
    }

    public void drawThis(){
        if(!discovered){
            System.out.print("#");
            return;
        }
        if(mine){
            System.out.print("*");
            return;
        }
        System.out.print(value);
    }

    public void mineIt(Field f){
        if(mine){
            f.incrementAround();
        }
    }
}
