package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the blur class which helps create an image manipulated by blurring it.
 */
public class Blur extends AbstractCommand {

  /**
   * Constructor that creates a Blur command.
   * @param image to be blurred
   * @param controller the controller
   * @param newImage name of the new image
   */
  public Blur(String image, ImageProcessingController controller, String newImage) {
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
    controller.printMessage(newImage + " created by blurring " + image + ".");
    return model.blurImage();
  }
}
