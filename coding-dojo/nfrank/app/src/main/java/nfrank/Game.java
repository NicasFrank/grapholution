package nfrank;

import java.util.Scanner;

public class Game {

    private final Field field = new Field();
    private final Scanner scanner = new Scanner(System.in);
    private boolean gameover = false;

    Game(){

    }

    public void play(){
        int x = 0;
        int y = 0;
        field.drawRow();
        while(!gameover){
            System.out.println("Bitte x-Wert eingeben");
            x = scanner.nextInt();
            System.out.println("Bitte y-Wert eingeben");
            y = scanner.nextInt();
            gameover = field.discoverSpot(x,y);
            field.drawRow();
        }
        System.out.println("Du hast verloren ... Sadge");
    }


}
