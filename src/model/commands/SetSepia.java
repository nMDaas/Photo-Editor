package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the image to sepia through the sepia filter.
 */
public class SetSepia extends PixelWiseProcessor {
  /**
   * Implements the command on the pixel.
   *
   * @param p the pixel.
   */
  @Override
  protected void doCommand(Pixel p) {
    p.setSepia();
  }
}
