package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the image to greyscale through the greyscale filter.
 */
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
