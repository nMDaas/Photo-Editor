package controller.commands;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;

/**
 * Represents the makesepia class which helps create an image manipulated through the
 * sepia filter.
 */
public class MakeSepia extends AbstractCommand {

  /**
   * Constructor that creates a new MakeSepia.
   * @param image the image name to be edited
   * @param controller the controller
   * @param newImage the name of the new edited image
   */
  public MakeSepia(String image, ImageProcessingModel model,
                        ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model   the ImageProcessingModel to perform the command on
   * @param control the ImageProcessingController to be passed
   * @return a new ImageProcessingModel
   */
  @Override
  protected ImageModel doCommand(ImageModel model,
                                 ImageProcessingController control) {
    controller.printMessage(newImage + " created through greyscale of " + image + ".");
    return model.setSepia();
  }
}
