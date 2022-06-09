package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

public class Brighten extends AbstractCommand {

  int value;

  public Brighten(String image, ImageProcessingController controller, int value, String newImage) {
    super(image, controller, newImage);
    this.value = value;
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created by changing brightness of " + image + ".");
    return model.brighten(this.value);
  }
}
