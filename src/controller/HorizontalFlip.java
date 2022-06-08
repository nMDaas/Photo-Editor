package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class HorizontalFlip extends AbstractCommand {

  String image;
  ImageProcessingController controller;
  String newImage;

  public HorizontalFlip(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model) {
    controller.printMessage(newImage + " created by horizontally flipping" + image + ".");
    return model.flipHorizontal();
  }
}
