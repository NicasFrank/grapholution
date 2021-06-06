package rkayser;
public class Minefield {
  private final Square[][] squares;
  private final MinefieldBuilder builder = new MinefieldBuilder();
  private final WLdetector detector;

  public Minefield(int fieldSize, int numberMines, WLdetector det){
    this.squares = builder.initMinefield(fieldSize, numberMines);
    this.detector=det;
  }
  public void drawMinefield(){
    for(int columnIndices = 0; columnIndices<squares.length+1;columnIndices++)
      System.out.print((columnIndices)+" ");
    System.out.println();
    for (int row=0; row<squares.length; row++){
      System.out.print(row+1+" ");
      drawColumns(row);
      System.out.println();
    }
  }
  public boolean revealSquare(int row, int column){
    if(squares[row][column].isVisibleDraw().equals("skip"))
      return false;
    if(squares[row][column].reveal())
      return true;
    if(squares[row][column].drawSquare().equals(" "))
      revealMore(row,column);
    detector.oneLessSquare();
    return false;
  }
  private void revealMore(int row, int column) {
    for (int xAround=-1; xAround<2; xAround++) {
      revealEvenMore(row+xAround,column);
    }
  }
  private void revealEvenMore(int row, int column) {
    for (int yAround=-1; yAround<2; yAround++) {
      catchOOB(row,column+yAround);
    }
  }
  private void catchOOB(int row,int column) {
    try {
      revealSquare(row,column);
    }catch (Exception ignored) {}
  }
  private void drawColumns(int row){
    for (int column=0; column<squares.length; column++)
      System.out.print(squares[row][column].drawSquare() + " ");
  }
}
