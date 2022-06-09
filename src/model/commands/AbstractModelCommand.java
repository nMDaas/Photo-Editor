package model.commands;

import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;

abstract public class AbstractModelCommand implements ModelCommand {
  @Override
  public ImageProcessingModel go(ImageProcessingModel model) {
    Pixel[][] newPixels = new Pixel [model.getHeight()][model.getWidth()];
    
    for (int row = 0; row <= (model.getHeight() - 1); row = row + 1) {
      for (int col = 0; col <= (model.getWidth() - 1); col = col + 1) {
        newPixels[row][col] = model.getPixelAt(row, col);
      }
    }
    
    ImageModel newImage =
            new ImageModel(model.getHeight(), model.getWidth(),
                    newPixels, model.getMax());

    for (int row = 0; row <= (newImage.getHeight() - 1); row = row + 1) {
      for (int col = 0; col <= (newImage.getWidth() - 1); col = col + 1) {
        doCommand(newImage.getPixelAt(row, col));
      }
    }

    return newImage;
  }

  abstract public void doCommand(Pixel p);
}
