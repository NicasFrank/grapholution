package rkayser;

import java.util.Random;

public class Minefield {
  private Square[][] squares;
  Random random = new Random();

  public Minefield(int fieldSize, int numberMines){
    this.squares = new Square[fieldSize][fieldSize];
    squaresInit();
    for (int i=0; i<numberMines; i++){
      squares[random.nextInt(fieldSize)][random.nextInt(fieldSize)] = new Square(true);
    }
  }
  public void drawMinefield(){
    for (int row=0; row<squares.length; row++){
      drawColumns(row);
      System.out.println();
    }
  }
  public boolean revealSquare(int row, int column){
    return squares[row][column].reveal();
  }

  private void drawColumns(int row){
    for (int column=0; column<squares.length; column++) {
      System.out.print(squares[row][column].drawSquare() + " ");
    }
  }
  private void squaresInit(){
    for (int row=0; row<squares.length; row++){
      initColumns(row);
    }
  }
  private void initColumns(int row){
    for (int column=0; column<squares.length; column++){
      squares[row][column] = new Square(false);
    }
  }
}
