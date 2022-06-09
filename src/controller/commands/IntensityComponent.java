package controller.commands;

import controller.ImageProcessingController;
import controller.commands.AbstractCommand;
import model.ImageProcessingModel;

public class IntensityComponent extends AbstractCommand {
  public IntensityComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through intensity greyscale of " + image + ".");
    return model.intensityComponent();
  }
}
