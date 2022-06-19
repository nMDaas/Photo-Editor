package model;

/**
 * Represents the posn class, which creates a position with a row, column and
 * multiplying factor that correlates to its position in the kernel.
 */
public class Posn {
  final int row;
  final int col;
  final double multiple;

  /**
   * Constructs a {@code Posn} with its fields initialized to themselves.
   * @param row the row of the posn.
   * @param col the col of the posn.
   * @param multiple the posn's multiple.
   */
  public Posn(int row, int col, double multiple) {
    this.row = row;
    this.col = col;
    this.multiple = multiple;
  }

  /**
   * Determines whether the posn is valid.
   * @param height the row of the posn.
   * @param width the col of the posn.
   * @return true if the posn is valid.
   */
  public boolean isValid(int height, int width) {
    return !(this.row < 0 || this.col < 0 || this.row >= height || this.col >= width);
  }
}
