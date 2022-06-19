package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the image to greyscale based on its green component.
 */
public class MakeGreen extends PixelWiseProcessor {

  /**
   * Manipulates the pixels to greyscale based on its green component.
   *
   * @param p the pixel.
   */
  @Override
  public void doCommand(Pixel p) {
    p.setColor(1);
  }
}
