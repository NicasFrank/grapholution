package rkayser;
import java.util.Random;

public class MinefieldBuilder {
  private Square[][] squares;
  private Random random = new Random();

  public Square[][] initMinefield(int fieldSize, int numberMines){
    squares = new Square[fieldSize][fieldSize];
    for (int i=0; i<numberMines; i++){
      initMines(random.nextInt(squares.length),random.nextInt(squares.length));
    }
    rowInit();
    return squares;
  }
  private void initMines(int row, int column) {
    if(squares[row][column]==null) {
      squares[row][column] = new Square(true,0);
      return;
    }
    initMines(random.nextInt(squares.length),random.nextInt(squares.length));
  }
  private void rowInit(){
    for (int row=0; row<squares.length; row++)
      initColumns(row);
  }
  private void initColumns(int row){
    for (int column=0; column<squares.length; column++)
      checkFreeSquare(row,column);
  }
  private void checkFreeSquare(int row, int column){
    if(squares[row][column]==null)
      initSquares(row,column);
  }
  private void initSquares(int row, int column) {
    int neighbourMines=0;
    for(int xAround=-1; xAround<2; xAround++)
      neighbourMines+=lookNeighbours(row,column,xAround);
    squares[row][column] = new Square(false,neighbourMines);
  }
  private int lookNeighbours(int row,int column,int xAround) {
    int neighbourMines=0;
    for (int yAround=-1; yAround<2; yAround++)
      neighbourMines+= catchExcept(row+xAround,column+yAround);
    return neighbourMines;
  }
  private int catchExcept(int row, int column) {
    try {
      return isMine(row,column);
    } catch (Exception e) {return 0;}
  }
  private int isMine(int row, int column) {
    if(squares[row][column].isMine())
      return 1;
    return 0;
  }
}
