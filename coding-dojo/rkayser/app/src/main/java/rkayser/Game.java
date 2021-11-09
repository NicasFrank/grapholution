package rkayser;

import java.util.Scanner;

public class Game {
  private final Minefield minefield;
  private boolean isLost;
  private final Scanner scanner = new Scanner(System.in);

  public Game(int fieldSize, int numberMines){
    this.minefield = new Minefield(fieldSize,numberMines);
    this.isLost=false;
  }

  public void play(){
    int[] XandY = new int[2];
    minefield.drawMinefield();
    while(!isLost){
      System.out.print("Bitte geben sie die Positon ein, die sie aufdecken wollen: \nx=");
      XandY[0]=scanner.nextInt();
      System.out.print("y=");
      XandY[1]=scanner.nextInt();
      isLost=minefield.revealSquare(XandY[0],XandY[1]);
      minefield.drawMinefield();
    }
    System.out.println("Du habe Verloré");
  }
}
