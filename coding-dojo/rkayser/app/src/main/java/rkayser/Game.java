package rkayser;

import java.util.Scanner;

public class Game {
  private final Minefield minefield;
  private final WLdetector detector;
  private final Scanner scannerG = new Scanner(System.in);

  public Game(int fieldSize, int numberMines){
    this.detector = new WLdetector(false,fieldSize,numberMines);
    this.minefield = new Minefield(fieldSize,numberMines,detector);
  }

  public void play(){
    int[] XandY;
    boolean end = false;
    minefield.drawMinefield();
    while(!end){
      XandY=coordinates();
      end=minefield.revealSquare(XandY[0]-1,XandY[1]-1);
      minefield.drawMinefield();
      end=detector.checkEnd(end);
    }
  }

  private int[] coordinates() {
    System.out.print("Bitte geben sie die Positon ein, die sie aufdecken wollen: \nx=");
    int x=scannerG.nextInt();
    System.out.print("y=");
    int y=scannerG.nextInt();
    return new int[] {x,y};
  }
}
