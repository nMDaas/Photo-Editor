package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the MakeGreyscale class which helps create an image manipulated through the
 * greyscale filter.
 */
public class MakeGreyscale extends AbstractCommand {

  /**
   * Constructor that creates a new MakeGreyScale.
   * @param image the image name to be edited
   * @param controller the controller
   * @param newImage the name of the new edited image
   */
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
  protected ImageProcessingModel doCommand(ImageProcessingModel model,
                                           ImageProcessingController control) {
    controller.printMessage(newImage + " created through greyscale of " + image + ".");
    return model.setGreyscale();
  }
}
