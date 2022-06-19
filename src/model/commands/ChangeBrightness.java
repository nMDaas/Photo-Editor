package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the brightness of the model.
 */
public class ChangeBrightness extends PixelWiseProcessor {
  private int val;

  /**
   * Constructs a {@code ChangeBrightness} with its field initialized to itself.
   *
   * @param val the brightness value.
   */
  public ChangeBrightness(int val) {
    this.val = val;
  }

  /**
   * Manipulates the brightness of the model.
   *
   * @param p the pixel.
   */
  @Override
  public void doCommand(Pixel p) {
    p.brighten(this.val);
  }
}
