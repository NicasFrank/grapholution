package rkayser;

public class Square {
  private boolean isMine;
  private boolean isVisible;

  public Square(boolean isMine){
    this.isMine = isMine;
    this.isVisible = false;
  }

  public String drawSquare(){
    if(!isVisible){
      return "#";
    }
    else if(isVisible&&isMine){
      return "X";
    }
    return " ";
  }
  public boolean reveal(){
    this.isVisible=true;
    return isMine;
  }
}