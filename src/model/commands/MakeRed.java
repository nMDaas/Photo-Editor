package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the image to greyscale based on its red component.
 */
public class MakeRed extends PixelWiseProcessor {

  /**
   * Manipulates the pixels to greyscale based on its red component.
   *
   * @param p the pixel.
   */
  @Override
  public void doCommand(Pixel p) {
    p.setColor(0);
  }
}
