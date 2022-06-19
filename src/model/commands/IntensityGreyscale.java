package model.commands;

import model.pixel.Pixel;

/**
 * Represents the command class that changes the image to greyscale based on its intensity.
 */
public class IntensityGreyscale extends PixelWiseProcessor {

  /**
   * Manipulates the pixel to greyscale based on its intensity.
   *
   * @param p the pixel.
   */
  @Override
  public void doCommand(Pixel p) {
    p.setIntensityComponent();
  }
}
