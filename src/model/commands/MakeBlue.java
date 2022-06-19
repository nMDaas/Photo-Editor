package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the image to greyscale based on its blue component.
 */
public class MakeBlue extends PixelWiseProcessor {

  /**
   * Manipulates the pixels to greyscale based on its blue component.
   *
   * @param p the pixel.
   */
  @Override
  public void doCommand(Pixel p) {
    p.setColor(2);
  }
}
