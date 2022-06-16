package model;

public class Posn {
  int row;
  int col;
  double multiple;

  public Posn(int row, int col, double multiple) {
    this.row = row;
    this.col = col;
    this.multiple = multiple;
  }

  boolean checkValid(int height, int width) {
    return !(this.row < 0 || this.col < 0 || this.row >= height || this.col >= width);
  }
}
