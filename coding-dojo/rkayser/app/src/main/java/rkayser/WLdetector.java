package rkayser;

public class WLdetector {
  private int squareAmount;
  private int numberMines;

  public WLdetector(boolean lose, int squares, int mines) {
    this.squareAmount=squares*squares;
    this.numberMines=mines;
  }

  public boolean checkEnd(boolean mineReveal) {
    if(mineReveal) {
      System.out.println("Sie haben verloren!");
      return true;
    }
    else if(squareAmount==numberMines) {
      System.out.println("Sie haben gewonnen!");
      return true;
    }
    return false;
  }
  public void oneLessSquare() {
    squareAmount--;
  }
}
