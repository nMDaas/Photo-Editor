package model.commands;

import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;

abstract public class PixelWiseProcessor {
  public ImageProcessingModel go(ImageProcessingModel model) {
    ImageProcessingModel newImage = model.createCopy();

    for (int row = 0; row <= (newImage.getHeight() - 1); row = row + 1) {
      for (int col = 0; col <= (newImage.getWidth() - 1); col = col + 1) {
        doCommand(newImage.getPixelAt(row, col));
      }
    }

    return newImage;
  }

  abstract public void doCommand(Pixel p);
}
