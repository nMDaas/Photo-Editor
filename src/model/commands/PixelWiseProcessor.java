package model.commands;

import model.ImageModel;
import model.pixel.Pixel;

/**
 * An abstract class for methods that create a new image that involve
 * mutating each pixel in the image the same way.
 */
abstract public class PixelWiseProcessor {

  /**
   * Creates a new ImageProcessingModel based on the command.
   *
   * @param model the model being manipulated.
   * @return the new ImageProcessingModel.
   */
  public ImageModel changePixels(ImageModel model) {
    ImageModel newImage = model.createCopy();

    for (int row = 0; row <= (newImage.getHeight() - 1); row = row + 1) {
      for (int col = 0; col <= (newImage.getWidth() - 1); col = col + 1) {
        doCommand(newImage.getPixelAt(row, col));
      }
    }

    return newImage;
  }

  /**
   * Implements the command on the pixel.
   *
   * @param p the pixel.
   */
  abstract protected void doCommand(Pixel p);
}
