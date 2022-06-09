package controller.commands;

import controller.ImageProcessingController;
import controller.commands.AbstractCommand;
import model.ImageProcessingModel;

public class RedComponent extends AbstractCommand {

  public RedComponent(String image, ImageProcessingController controller, String newImage) {
    super (image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through red channel of " + image + ".");
    return model.redComponent();
  }
}
