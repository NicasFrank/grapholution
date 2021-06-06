package rkayser;

public class Square {
  private final boolean isMine;
  private boolean isVisible;
  private final int neighbourMines;

  public Square(boolean isMine,int neighbourMines){
    this.isMine = isMine;
    this.isVisible = false;
    this.neighbourMines = neighbourMines;
  }
  public String isVisibleDraw() {
    if(isVisible)
      return "skip";
    return drawSquare();
  }
  public String drawSquare(){
    if(!isVisible){
      return "#";
    }
    else if(isVisible&&isMine){
      return "X";
    }
    else if(isVisible&&(neighbourMines!=0)) {
      return String.valueOf(neighbourMines);
    }
    return " ";
  }
  public boolean reveal(){
    this.isVisible=true;
    return isMine;
  }
  public boolean isMine() {
    return isMine;
  }
}
