package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the image to greyscale based on its luma.
 */
public class LumaGreyscale extends PixelWiseProcessor {

  /**
   * Manipulates the pixels to greyscale based on its luma.
   *
   * @param p the pixel.
   */
  @Override
  public void doCommand(Pixel p) {
    p.setLumaComponent();
  }
}
