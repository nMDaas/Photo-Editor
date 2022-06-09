package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

public class BlueComponent extends AbstractCommand {

  public BlueComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through blue channel of " + image + ".");
    return model.blueComponent();
  }
}
