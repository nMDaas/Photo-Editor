package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Sharpen represents the command class to sharpen an image.
 */
public class Sharpen extends AbstractCommand {

  /**
   * Creates a Sharpen class.
   * @param image the name of the image to be edited
   * @param controller the controller
   * @param newImage the name of the edited image
   */
  public Sharpen(String image, ImageProcessingController controller, String newImage) {
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
    controller.printMessage(newImage + " created by sharpening " + image + ".");
    return model.sharpenImage();
  }
}
