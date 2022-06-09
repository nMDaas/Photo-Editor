package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

public class GreenComponent extends AbstractCommand {

  public GreenComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through green channel of " + image + ".");
    return model.greenComponent();
  }
}
