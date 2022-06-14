package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

public class MakeGreyscale extends AbstractCommand {

  public MakeGreyscale(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model   the ImageProcessingModel to perform the command on
   * @param control the ImageProcessingController to be passed
   * @return a new ImageProcessingModel
   */
  @Override
  protected ImageProcessingModel doCommand(ImageProcessingModel model, ImageProcessingController control) {
    controller.printMessage(newImage + " created through greyscale of " + image + ".");
    return model.setGreyscale();
  }
}
