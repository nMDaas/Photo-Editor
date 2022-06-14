package model.commands;

import model.pixel.Pixel;

public class SetGreyscale extends PixelWiseProcessor {
  /**
   * Implements the command on the pixel.
   *
   * @param p the pixel.
   */
  @Override
  protected void doCommand(Pixel p) {
    p.setGreyscale();
  }
}
