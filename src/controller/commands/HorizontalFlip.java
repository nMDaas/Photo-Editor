package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

public class HorizontalFlip extends AbstractCommand {

  public HorizontalFlip(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created by horizontally flipping " + image + ".");
    return model.flipHorizontal();
  }
}
